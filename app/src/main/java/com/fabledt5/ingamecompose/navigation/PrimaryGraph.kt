package com.fabledt5.ingamecompose.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.fabledt5.catalogue.screens.CatalogueScreen
import com.fabledt5.home.screens.HomeScreen
import com.fabledt5.navigation.Routes
import com.fabledt5.navigation.directions.PrimaryAppDirections
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalMaterial3Api
@ExperimentalPagerApi
@ExperimentalFoundationApi
@ExperimentalCoroutinesApi
@ExperimentalAnimationApi
fun NavGraphBuilder.primaryGraph(viewModelStoreOwner: ViewModelStoreOwner) {
    navigation(
        startDestination = PrimaryAppDirections.home.route,
        route = Routes.PRIMARY
    ) {
        composable(route = PrimaryAppDirections.home.route) {
            HomeScreen(
                homeViewModel = hiltViewModel(
                    viewModelStoreOwner = viewModelStoreOwner
                )
            )
        }
        composable(PrimaryAppDirections.catalogue.route) {
            CatalogueScreen(
                catalogueViewModel = hiltViewModel(
                    viewModelStoreOwner = viewModelStoreOwner
                )
            )
        }
    }
}