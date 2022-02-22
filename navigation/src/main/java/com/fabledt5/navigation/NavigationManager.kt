package com.fabledt5.navigation

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class NavigationManager {

    private val _commands = MutableSharedFlow<NavigationCommand>(1)
    val commands = _commands.asSharedFlow()

    fun navigate(
        directions: NavigationCommand
    ) {
        _commands.tryEmit(directions)
    }

}