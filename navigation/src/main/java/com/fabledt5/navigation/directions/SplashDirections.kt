package com.fabledt5.navigation.directions

import androidx.navigation.NamedNavArgument
import com.fabledt5.navigation.NavigationCommand

object SplashDirections {

    val splash = object : NavigationCommand {

        override val arguments = emptyList<NamedNavArgument>()

        override val route = "splash"

        override val inclusive = true

    }
}