package com.fabledt5.navigation.directions

import androidx.navigation.NamedNavArgument
import com.fabledt5.navigation.NavigationCommand

object BackDirection {

    val back = object : NavigationCommand {

        override val arguments = emptyList<NamedNavArgument>()

        override val route = ""

        override val inclusive = false

    }

}