package com.fabledt5.ingamecompose.navigation

import android.app.Activity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import com.fabledt5.game.GameViewModel
import com.fabledt5.game.screens.GameScreen
import com.fabledt5.game.screens.ReviewsScreen
import com.fabledt5.ingamecompose.MainActivity
import com.fabledt5.navigation.directions.GameDirections
import com.google.accompanist.navigation.animation.composable
import dagger.hilt.android.EntryPointAccessors

@ExperimentalAnimationApi
fun NavGraphBuilder.gameGraph(
    viewModelStoreOwner: ViewModelStoreOwner,
    onGamePageSelected: () -> Unit,
    onGamePageUnselected: () -> Unit
) {
    composable(
        route = GameDirections.gameScreenRoute,
        arguments = GameDirections.gameArguments
    ) { navBackStackEntry ->
        val gameId = navBackStackEntry.arguments?.getInt(GameDirections.KEY_GAME_ID)
        requireNotNull(gameId)

        val gameViewModelFactory = EntryPointAccessors.fromActivity(
            LocalContext.current as Activity,
            MainActivity.ViewModelFactoryProvider::class.java
        ).gameViewModelFactory()

        val model = viewModel<GameViewModel>(
            viewModelStoreOwner = viewModelStoreOwner,
            factory = GameViewModel.provideFactory(
                assistedFactory = gameViewModelFactory,
                gameId = gameId
            ),
            key = gameId.toString()
        )

        onGamePageSelected()
        GameScreen(gameViewModel = model)
    }

    composable(
        route = GameDirections.gameReviewsRoute,
        arguments = GameDirections.gameArguments
    ) { navBackStackEntry ->
        val gameId = navBackStackEntry.arguments?.getInt(GameDirections.KEY_GAME_ID)
        requireNotNull(gameId)

        CompositionLocalProvider(LocalViewModelStoreOwner provides viewModelStoreOwner) {
            val model = viewModel<GameViewModel>(key = gameId.toString())
            onGamePageUnselected()
            ReviewsScreen(gameViewModel = model)
        }
    }

}