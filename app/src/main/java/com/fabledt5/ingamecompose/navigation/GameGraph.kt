package com.fabledt5.ingamecompose.navigation

import android.app.Activity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import com.fabledt5.game.GameViewModel
import com.fabledt5.game.screens.GameScreen
import com.fabledt5.game.screens.ReviewsScreen
import com.fabledt5.ingamecompose.MainActivity
import com.fabledt5.navigation.directions.GameDirections
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalPagerApi
@ExperimentalAnimationApi
fun NavGraphBuilder.gameGraph() {

    composable(
        route = GameDirections.gameScreenRoute,
        arguments = GameDirections.gameArguments
    ) { navBackStackEntry ->
        val gameId = navBackStackEntry.arguments?.getInt(GameDirections.KEY_GAME_ID)
        requireNotNull(gameId)
        GameScreen(gameViewModel = gameViewModel(gameId = gameId))
    }

    composable(
        route = GameDirections.gameReviewsRoute,
        arguments = GameDirections.gameArguments
    ) { navBackStackEntry ->
        val gameId = navBackStackEntry.arguments?.getInt(GameDirections.KEY_GAME_ID)
        requireNotNull(gameId)
        ReviewsScreen(gameViewModel = gameViewModel(gameId = gameId))
    }

}

@ExperimentalCoroutinesApi
@ExperimentalAnimationApi
@ExperimentalPagerApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun gameViewModel(gameId: Int): GameViewModel {
    val factory = EntryPointAccessors.fromActivity(
        LocalContext.current as Activity,
        MainActivity.ViewModelFactoryProvider::class.java
    ).gameViewModelFactory()

    return viewModel(factory = GameViewModel.provideFactory(factory, gameId = gameId))
}