package com.fabledt5.navigation.directions

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.fabledt5.navigation.NavigationCommand

object GameDirection {

    const val KEY_GAME_ID = "gameId"

    val route = "game/{$KEY_GAME_ID}"

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

}