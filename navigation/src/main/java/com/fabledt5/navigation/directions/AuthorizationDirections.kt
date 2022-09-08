package com.fabledt5.navigation.directions

import androidx.navigation.NamedNavArgument
import com.fabledt5.navigation.NavigationCommand

object AuthorizationDirections {

    val authorization = object : NavigationCommand {

        override val arguments = emptyList<NamedNavArgument>()

        override val route = "authorization"

        override val inclusive = false

    }
}