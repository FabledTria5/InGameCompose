package com.fabledt5.navigation.directions

import androidx.navigation.NamedNavArgument
import com.fabledt5.navigation.NavigationCommand

object PrimaryAppDirections {
    val home = object : NavigationCommand {
        override val arguments = emptyList<NamedNavArgument>()
        override val route = "home"
        override val inclusive = false
    }

    val catalogue = object : NavigationCommand {
        override val arguments = emptyList<NamedNavArgument>()
        override val route = "catalogue"
        override val inclusive = false
    }

    val collections = object : NavigationCommand {
        override val arguments = emptyList<NamedNavArgument>()
        override val route = "collections"
        override val inclusive = false
    }
}