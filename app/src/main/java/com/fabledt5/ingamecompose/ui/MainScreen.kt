package com.fabledt5.ingamecompose.ui

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.fabledt5.common.theme.DefaultHorizontalGradient
import com.fabledt5.common.theme.DimGray
import com.fabledt5.common.theme.Mark
import com.fabledt5.common.utils.gradient
import com.fabledt5.ingamecompose.navigation.authenticationGraph
import com.fabledt5.ingamecompose.navigation.gameGraph
import com.fabledt5.ingamecompose.navigation.primaryGraph
import com.fabledt5.ingamecompose.utils.BottomBarItem
import com.fabledt5.ingamecompose.utils.setPrimaryColor
import com.fabledt5.ingamecompose.utils.setTransparentStatusBar
import com.fabledt5.navigation.NavigationManager
import com.fabledt5.navigation.Routes
import com.fabledt5.navigation.directions.BackDirection
import com.fabledt5.navigation.directions.GameDirections
import com.fabledt5.navigation.directions.PrimaryAppDirections
import com.fabledt5.navigation.directions.SplashDirections
import com.fabledt5.splash.SplashScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@ExperimentalPagerApi
@ExperimentalAnimationApi
@Composable
fun MainScreen(navigationManager: NavigationManager) {
    val systemUiController = rememberSystemUiController()
    val viewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {
        "No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"
    }
    val navHostController = rememberAnimatedNavController()
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route
    var inclusiveScreen by remember { mutableStateOf(true) }

    LaunchedEffect(key1 = navigationManager.commands) {
        navigationManager.commands.collectLatest { command ->
            navHostController.navigate(command.route) {
                if (inclusiveScreen)
                    navHostController.currentDestination?.route?.let { route ->
                        popUpTo(route = route) { inclusive = true }
                    }
            }
            inclusiveScreen = command.inclusive
        }
    }

    LaunchedEffect(key1 = navigationManager.backNavigation) {
        navigationManager.backNavigation.collectLatest { command ->
            if (command == BackDirection.back) {
                navHostController.popBackStack()
            }
        }
    }

    LaunchedEffect(key1 = currentDestination) {
        when (currentDestination) {
            SplashDirections.splash.route -> systemUiController.setTransparentStatusBar()
            GameDirections.gameScreenRoute -> systemUiController.setTransparentStatusBar()
            PrimaryAppDirections.home.route -> systemUiController.setTransparentStatusBar()
            else -> systemUiController.setPrimaryColor()
        }
    }

    Scaffold(bottomBar = {
        BottomBar(
            navHostController = navHostController,
            currentDestination = currentDestination
        )
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
            gameGraph(
                viewModelStoreOwner = viewModelStoreOwner,
                onGamePageSelected = { systemUiController.setTransparentStatusBar() },
                onGamePageUnselected = { systemUiController.setPrimaryColor() }
            )
        }
    }
}

@Composable
fun BottomBar(navHostController: NavHostController, currentDestination: String?) {
    val screens = listOf(
        BottomBarItem.Home,
        BottomBarItem.Catalogue,
        BottomBarItem.Collections,
        BottomBarItem.Profile
    )

    val isBottomNavigationVisible = !currentDestination.isNullOrEmpty() && screens.any { screen ->
        screen.destination.route == currentDestination
    }
    val bottomNavigationHeight by animateDpAsState(
        targetValue = if (isBottomNavigationVisible) 56.dp else 0.dp,
        animationSpec = tween(durationMillis = 100, easing = LinearOutSlowInEasing)
    )

    BottomNavigation(
        backgroundColor = Color.Black,
        modifier = Modifier
            .navigationBarsPadding()
            .height(bottomNavigationHeight)
    ) {
        screens.forEach { screen ->
            AddNavigationItem(
                screen = screen,
                navHostController = navHostController,
                currentDestination = currentDestination
            )
        }
    }
}

@Composable
fun RowScope.AddNavigationItem(
    screen: BottomBarItem,
    navHostController: NavHostController,
    currentDestination: String?
) {
    val selected = currentDestination == screen.destination.route
    BottomNavigationItem(
        selected = selected,
        onClick = { navHostController.navigate(screen.destination.route) },
        label = {
            Text(
                text = screen.title,
                modifier = Modifier.then(
                    if (selected) Modifier.gradient(DefaultHorizontalGradient) else Modifier
                ),
                fontFamily = Mark
            )
        },
        icon = {
            Icon(
                painter = painterResource(id = screen.icon),
                contentDescription = null,
                modifier = Modifier.then(
                    if (selected) Modifier.gradient(DefaultHorizontalGradient) else Modifier
                )
            )
        },
        unselectedContentColor = DimGray,
        selectedContentColor = DimGray
    )
}