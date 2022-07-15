package com.fabledt5.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fabledt5.domain.use_case.authentication.IsAuthenticated
import com.fabledt5.navigation.NavigationManager
import com.fabledt5.navigation.directions.AuthorizationDirections
import com.fabledt5.navigation.directions.PrimaryAppDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
    isAuthenticated: IsAuthenticated
) : ViewModel() {

    private val _isUserAuthenticated = MutableStateFlow(false)
    val isUserAuthenticated = _isUserAuthenticated.asStateFlow()

    init {
        isAuthenticated()
            .onEach {
                _isUserAuthenticated.value = it
            }
            .catch { exception ->
                Timber.e(exception)
            }
            .flowOn(Dispatchers.IO)
            .launchIn(viewModelScope)
    }

    fun openLoginScreen() = navigationManager.navigate(AuthorizationDirections.authorization)

    fun openHomeScreen() = navigationManager.navigate(PrimaryAppDirections.home)

}