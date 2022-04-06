package com.fabledt5.navigation

import com.fabledt5.navigation.directions.BackDirection
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class NavigationManager {

    private val _commands = MutableSharedFlow<NavigationCommand>(2)
    val commands = _commands.asSharedFlow()

    private val _backNavigation = MutableSharedFlow<NavigationCommand>(1)
    val backNavigation = _backNavigation.asSharedFlow()

    fun navigate(directions: NavigationCommand) = _commands.tryEmit(directions)

    fun navigateBack() = _backNavigation.tryEmit(BackDirection.back)

}