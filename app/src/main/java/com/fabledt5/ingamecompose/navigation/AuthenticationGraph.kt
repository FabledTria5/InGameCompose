package com.fabledt5.ingamecompose.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.fabledt5.authentication.screens.AuthenticationScreen
import com.fabledt5.authentication.screens.PasswordRecoveryScreen
import com.fabledt5.navigation.Routes
import com.fabledt5.navigation.directions.AuthorizationDirections
import com.google.accompanist.navigation.animation.composable

@ExperimentalAnimationApi
fun NavGraphBuilder.authenticationGraph() {
    navigation(
        startDestination = AuthorizationDirections.authorization.route,
        route = Routes.AUTHENTICATION
    ) {
        composable(route = AuthorizationDirections.authorization.route) {
            AuthenticationScreen(authenticationViewModel = hiltViewModel())
        }
        composable(
            route = AuthorizationDirections.passwordRecovery.route,
            enterTransition = {
                slideInHorizontally(
                    animationSpec = tween(durationMillis = 300),
                    initialOffsetX = { it })
            },
            popExitTransition = {
                slideOutHorizontally(
                    animationSpec = tween(durationMillis = 300),
                    targetOffsetX = { it }
                )
            }
        ) {
            PasswordRecoveryScreen(authenticationViewModel = hiltViewModel())
        }
    }
}