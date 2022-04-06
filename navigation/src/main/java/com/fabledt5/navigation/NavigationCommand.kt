package com.fabledt5.navigation

import androidx.navigation.NamedNavArgument

interface NavigationCommand {

    val arguments: List<NamedNavArgument>

    val route: String

    val inclusive: Boolean

    companion object : NavigationCommand {

        override val arguments = emptyList<NamedNavArgument>()

        override val route = ""

        override val inclusive = false

    }

}