package com.fabledt5.ingamecompose.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.fabledt5.common.theme.DarkLateGray
import com.fabledt5.common.theme.Mark
import com.fabledt5.common.theme.Turquoise
import com.fabledt5.ingamecompose.navigation.authenticationGraph
import com.fabledt5.ingamecompose.navigation.gameComposable
import com.fabledt5.ingamecompose.navigation.primaryGraph
import com.fabledt5.navigation.BottomBarItem
import com.fabledt5.navigation.NavigationCommand
import com.fabledt5.navigation.NavigationManager
import com.fabledt5.navigation.Routes
import com.fabledt5.navigation.directions.GameDirection
import com.fabledt5.navigation.directions.PrimaryAppDirections
import com.fabledt5.navigation.directions.SplashDirections
import com.fabledt5.splash.SplashScreen
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@ExperimentalPagerApi
@ExperimentalAnimationApi
@Composable
fun MainScreen(navigationManager: NavigationManager) {
    val navHostController = rememberAnimatedNavController()
    var currentDestination by remember { mutableStateOf(SplashDirections.splash) }

    val bottomNavigationDestinations = listOf(
        PrimaryAppDirections.home,
        PrimaryAppDirections.catalogue,
        PrimaryAppDirections.collections,
        PrimaryAppDirections.profile
    )

    LaunchedEffect(key1 = navigationManager.commands) {
        navigationManager.commands.collectLatest { command ->
            require(command.route.isNotEmpty())
            navHostController.navigate(command.route) {
                if (currentDestination.inclusive)
                    popUpTo(currentDestination.route) { inclusive = true }
                currentDestination = command
            }
        }
    }

    Scaffold(bottomBar = {
        if (currentDestination in bottomNavigationDestinations)
            BottomBar(navHostController, currentDestination)
    }) {
        AnimatedNavHost(
            navController = navHostController,
            startDestination = SplashDirections.splash.route,
            route = Routes.ROOT,
            modifier = Modifier.fillMaxSize()
        ) {
            composable(route = SplashDirections.splash.route) {
                SplashScreen(splashViewModel = hiltViewModel())
            }
            authenticationGraph()
            primaryGraph()
            gameComposable()
        }
    }
}

@Composable
fun BottomBar(navHostController: NavHostController, currentDestination: NavigationCommand) {
    val screens = listOf(
        BottomBarItem.Home,
        BottomBarItem.Catalogue,
        BottomBarItem.Collections,
        BottomBarItem.Profile
    )

    BottomNavigation(backgroundColor = Color.Black, modifier = Modifier.navigationBarsPadding()) {
        screens.forEach { screen ->
            AddNavigationItem(
                screen = screen,
                currentDestination = currentDestination,
                navHostController = navHostController
            )
        }
    }
}

@Composable
fun RowScope.AddNavigationItem(
    screen: BottomBarItem,
    currentDestination: NavigationCommand,
    navHostController: NavHostController
) {
    BottomNavigationItem(
        selected = currentDestination == screen.destination,
        onClick = { navHostController.navigate(screen.destination.route) },
        label = { Text(text = screen.title, fontFamily = Mark, color = DarkLateGray) },
        icon = {
            Icon(
                painter = painterResource(id = screen.icon),
                contentDescription = null
            )
        },
        selectedContentColor = Turquoise,
        unselectedContentColor = Color.Unspecified
    )
}