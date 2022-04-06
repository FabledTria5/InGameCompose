package com.fabledt5.navigation.directions

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.fabledt5.navigation.NavigationCommand

object GameDirections {

    const val KEY_GAME_ID = "gameId"

    const val gameScreenRoute = "game/{$KEY_GAME_ID}"
    const val gameReviewsRoute = "game/{$KEY_GAME_ID}/reviews"

    val gameArguments = listOf(
        navArgument(KEY_GAME_ID) {
            type = NavType.IntType
        }
    )

    fun game(gameId: Int) = object : NavigationCommand {

        override val arguments = gameArguments

        override val route = "game/$gameId"

        override val inclusive = false

    }

    fun reviews(gameId: Int) = object : NavigationCommand {

        override val arguments = gameArguments

        override val route = "game/$gameId/reviews"

        override val inclusive = false

    }

}