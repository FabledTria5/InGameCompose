package com.fabledt5.navigation

import androidx.navigation.NamedNavArgument

interface NavigationCommand {

    val arguments: List<NamedNavArgument>

    val route: String

    val inclusive: Boolean

}