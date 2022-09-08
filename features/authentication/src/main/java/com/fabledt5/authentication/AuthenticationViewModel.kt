package com.fabledt5.authentication

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fabledt5.authentication.model.AuthenticationFormEvent
import com.fabledt5.authentication.model.AuthenticationFormState
import com.fabledt5.domain.model.Resource
import com.fabledt5.domain.use_case.authentication.AuthenticationCases
import com.fabledt5.domain.use_case.authentication.validation.ValidationCases
import com.fabledt5.navigation.NavigationManager
import com.fabledt5.navigation.directions.PrimaryAppDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
@Stable
class AuthenticationViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
    private val authenticationCases: AuthenticationCases,
    private val validationCases: ValidationCases
) : ViewModel() {

    private val _signInState = MutableStateFlow(AuthenticationFormState())
    val signInState = _signInState.asStateFlow()

    private val _signUpState = MutableStateFlow(AuthenticationFormState())
    val signUpState = _signUpState.asStateFlow()

    private val _authenticationState = MutableStateFlow<Resource<Boolean>>(Resource.Idle)
    val authenticationState = _authenticationState.asStateFlow()

    init {
        getUserAuthenticationStatus()
    }

    private fun submitSignInData() {
        val emailResult = validationCases.validateEmail(_signInState.value.email)
        val passwordResult = validationCases.validatePassword(_signInState.value.password)

        val hasError = listOf(emailResult, passwordResult).any { !it.isSuccessful }
        if (hasError) {
            _signInState.value = _signInState.value.copy(
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage
            )
            return
        }
        authenticateUser()
    }

    private fun submitSignUpData() {
        val emailResult = validationCases.validateEmail(_signUpState.value.email)
        val passwordResult = validationCases.validatePassword(_signUpState.value.password)
        val nickNameResult = validationCases.validateNickname(_signUpState.value.nickname)

        val hasError = listOf(emailResult, passwordResult, nickNameResult).any { !it.isSuccessful }
        if (hasError) {
            _signUpState.value = _signUpState.value.copy(
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage,
                nicknameError = nickNameResult.errorMessage
            )
            return
        }
        registerUser()
    }

    private fun getUserAuthenticationStatus() =
        authenticationCases.checkAuthentication().onEach { isAuthenticated ->
            if (isAuthenticated) openHomePage()
        }.launchIn(viewModelScope)

    private fun openHomePage() = navigationManager.navigate(PrimaryAppDirections.home)

    private fun registerUser() = authenticationCases.registerUser(
        email = _signUpState.value.email,
        password = _signUpState.value.password,
        nickName = _signUpState.value.nickname
    )
        .onEach { resource ->
            _authenticationState.value = resource
        }
        .flowOn(Dispatchers.IO)
        .launchIn(viewModelScope)

    private fun authenticateUser() = authenticationCases.authenticateUser(
        userEmail = _signInState.value.email,
        userPassword = _signInState.value.password
    )
        .onEach { resource ->
            _authenticationState.value = resource
        }
        .flowOn(Dispatchers.IO)
        .launchIn(viewModelScope)

    fun onSignInEvent(event: AuthenticationFormEvent) {
        when (event) {
            is AuthenticationFormEvent.EmailChanged -> {
                _signInState.value = _signInState.value.copy(email = event.email)
            }
            is AuthenticationFormEvent.PasswordChanged -> {
                _signInState.value = _signInState.value.copy(password = event.password)
            }
            AuthenticationFormEvent.Submit -> submitSignInData()
            else -> Unit
        }
    }

    fun onSignUpEvent(event: AuthenticationFormEvent) {
        when (event) {
            is AuthenticationFormEvent.EmailChanged -> {
                _signUpState.value = _signUpState.value.copy(email = event.email)
            }
            is AuthenticationFormEvent.NicknameChanged -> {
                _signUpState.value = _signUpState.value.copy(nickname = event.nickname)
            }
            is AuthenticationFormEvent.PasswordChanged -> {
                _signUpState.value = _signUpState.value.copy(password = event.password)
            }
            AuthenticationFormEvent.Submit -> submitSignUpData()
        }
    }

}