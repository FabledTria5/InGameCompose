package com.fabledt5.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fabledt5.domain.model.Resource
import com.fabledt5.domain.use_case.authentication.AuthenticationCases
import com.fabledt5.navigation.NavigationManager
import com.fabledt5.navigation.directions.AuthorizationDirections
import com.fabledt5.navigation.directions.PrimaryAppDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
    private val authenticationCases: AuthenticationCases
) : ViewModel() {

    private val _registrationState = MutableStateFlow<Resource<Boolean>>(Resource.Idle)
    val registrationState = _registrationState.asStateFlow()

    private val _loginState = MutableStateFlow<Resource<Boolean>>(Resource.Idle)
    val loginState = _loginState.asStateFlow()

    private val _passwordRecoveryState = MutableStateFlow<Resource<Boolean>>(Resource.Idle)
    val passwordRecoveryState = _passwordRecoveryState.asStateFlow()

    fun openPasswordRecoveryScreen() =
        navigationManager.navigate(AuthorizationDirections.passwordRecovery)

    fun registerUser(userEmail: String, userPassword: String, userNickname: String) =
        authenticationCases.registerUser(userEmail, userPassword, userNickname).onEach { resource ->
            _registrationState.value = resource
        }.launchIn(viewModelScope)

    fun authenticateUser(userEmail: String, userPassword: String) =
        authenticationCases.authenticateUser(userEmail, userPassword).onEach { resource ->
            if (resource is Resource.Success) {
                navigationManager.navigate(PrimaryAppDirections.home)
            }
            _loginState.value = resource
        }.launchIn(viewModelScope)

}