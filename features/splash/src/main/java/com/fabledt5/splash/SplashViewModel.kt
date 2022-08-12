package com.fabledt5.splash

import androidx.lifecycle.ViewModel
import com.fabledt5.domain.use_case.authentication.IsAuthenticated
import com.fabledt5.navigation.NavigationManager
import com.fabledt5.navigation.directions.AuthorizationDirections
import com.fabledt5.navigation.directions.PrimaryAppDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
    isAuthenticated: IsAuthenticated
) : ViewModel() {

    private val _isUserAuthenticated = MutableStateFlow(value = false)
    val isUserAuthenticated = _isUserAuthenticated.asStateFlow()

    init {
        _isUserAuthenticated.value = isAuthenticated()
    }

    fun openLoginScreen() = navigationManager.navigate(AuthorizationDirections.authorization)

    fun openHomeScreen() = navigationManager.navigate(PrimaryAppDirections.home)

}