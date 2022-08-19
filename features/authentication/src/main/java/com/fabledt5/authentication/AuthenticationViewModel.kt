package com.fabledt5.authentication

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fabledt5.authentication.model.AuthenticationFormEvent
import com.fabledt5.authentication.model.AuthenticationFormState
import com.fabledt5.domain.model.Resource
import com.fabledt5.domain.use_case.authentication.AuthenticationCases
import com.fabledt5.domain.use_case.authentication.validation.ValidationCases
import com.fabledt5.navigation.NavigationManager
import com.fabledt5.navigation.directions.AuthorizationDirections
import com.fabledt5.navigation.directions.PrimaryAppDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
    private val authenticationCases: AuthenticationCases,
    private val validationCases: ValidationCases
) : ViewModel() {

    var signInState by mutableStateOf(AuthenticationFormState())

    var signUpState by mutableStateOf(AuthenticationFormState())

    var registrationState by mutableStateOf<Resource<String>>(Resource.Idle)
        private set

    var loginState by mutableStateOf<Resource<Boolean>>(Resource.Idle)
        private set

    init {
        getUserAuthenticationStatus()
    }

    fun onSignInEvent(event: AuthenticationFormEvent) {
        when (event) {
            is AuthenticationFormEvent.EmailChanged -> {
                signInState = signUpState.copy(email = event.email)
            }
            is AuthenticationFormEvent.PasswordChanged -> {
                signInState = signInState.copy(password = event.password)
            }
            AuthenticationFormEvent.Submit -> submitSignInData()
            else -> Unit
        }
    }

    private fun submitSignInData() {
        val emailResult = validationCases.validateEmail(signInState.email)
        val passwordResult = validationCases.validatePassword(signInState.password)

        val hasError = listOf(emailResult, passwordResult).any { !it.isSuccessful }
        if (hasError) {
            signInState = signInState.copy(
                emailError = emailResult.errorMessage, passwordError = passwordResult.errorMessage
            )
            return
        }
        authenticateUser()
    }

    fun onSignUpEvent(event: AuthenticationFormEvent) {
        when (event) {
            is AuthenticationFormEvent.EmailChanged -> {
                signUpState = signUpState.copy(email = event.email)
            }
            is AuthenticationFormEvent.NicknameChanged -> {
                signUpState = signUpState.copy(nickname = event.nickname)
            }
            is AuthenticationFormEvent.PasswordChanged -> {
                signUpState = signUpState.copy(password = event.password)
            }
            AuthenticationFormEvent.Submit -> submitSignUpData()
        }
    }

    private fun submitSignUpData() {
        val emailResult = validationCases.validateEmail(signUpState.email)
        val passwordResult = validationCases.validatePassword(signUpState.password)
        val nickNameResult = validationCases.validateNickname(signUpState.nickname)

        val hasError = listOf(emailResult, passwordResult, nickNameResult).any { !it.isSuccessful }
        if (hasError) {
            signUpState = signUpState.copy(
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
        email = signUpState.email, password = signUpState.password, nickName = signUpState.nickname
    )
        .onEach { resource ->
            registrationState = resource
        }
        .flowOn(Dispatchers.IO)
        .launchIn(viewModelScope)

    private fun authenticateUser() = authenticationCases.authenticateUser(
        userEmail = signInState.email,
        userPassword = signInState.password
    )
        .onEach { resource ->
            loginState = resource
        }
        .flowOn(Dispatchers.IO)
        .launchIn(viewModelScope)

    fun openPasswordRecoveryScreen() =
        navigationManager.navigate(AuthorizationDirections.passwordRecovery)

}