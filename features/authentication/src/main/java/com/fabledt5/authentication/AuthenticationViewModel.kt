package com.fabledt5.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fabledt5.domain.model.Resource
import com.fabledt5.domain.use_case.authentication.AuthenticationCases
import com.fabledt5.navigation.NavigationManager
import com.fabledt5.navigation.directions.AuthorizationDirections
import com.fabledt5.navigation.directions.PrimaryAppDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
    private val authenticationCases: AuthenticationCases
) : ViewModel() {

    private val _registrationState = MutableStateFlow<Resource<Any>>(Resource.Idle)
    val registrationState = _registrationState.asStateFlow()

    private val _loginState = MutableStateFlow<Resource<Any>>(Resource.Idle)
    val loginState = _loginState.asStateFlow()

    private val _passwordRecoveryState = MutableStateFlow<Resource<Any>>(Resource.Idle)
    val passwordRecoveryState = _passwordRecoveryState.asStateFlow()

    init {
        getUserAuthenticationStatus()
    }

    private fun getUserAuthenticationStatus() = authenticationCases.checkAuthentication()
        .onEach { isAuthenticated ->
            if (isAuthenticated) openHomePage()
        }
        .launchIn(viewModelScope)

    private fun openHomePage() = navigationManager.navigate(PrimaryAppDirections.home)

    fun openPasswordRecoveryScreen() =
        navigationManager.navigate(AuthorizationDirections.passwordRecovery)

    fun recoverPassword(userEmail: String) {

    }

    fun registerUser(userEmail: String, userPassword: String, userNickname: String) =
        authenticationCases.registerUser(userEmail, userPassword, userNickname)
            .onEach { resource ->
                _registrationState.value = resource
            }
            .flowOn(Dispatchers.IO)
            .launchIn(viewModelScope)

    fun authenticateUser(userEmail: String, userPassword: String) =
        authenticationCases.authenticateUser(userEmail, userPassword).onEach { resource ->
            _loginState.value = resource
        }
            .flowOn(Dispatchers.IO)
            .launchIn(viewModelScope)

}