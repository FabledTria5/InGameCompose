package com.fabledt5.ingamecompose.navigation

import android.app.Activity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import com.fabledt5.game.GameViewModel
import com.fabledt5.game.screens.GameScreen
import com.fabledt5.ingamecompose.MainActivity
import com.fabledt5.navigation.directions.GameDirection
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalPagerApi
@ExperimentalAnimationApi
fun NavGraphBuilder.gameComposable() {

    composable(
        route = GameDirection.route,
        arguments = GameDirection.gameArguments
    ) { navBackStackEntry ->
        val gameId = navBackStackEntry.arguments?.getInt(GameDirection.KEY_GAME_ID)
        requireNotNull(gameId)
        GameScreen()
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