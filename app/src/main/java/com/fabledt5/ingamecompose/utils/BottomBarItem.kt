package com.fabledt5.ingamecompose.utils

import androidx.annotation.DrawableRes
import com.fabledt5.navigation.NavigationCommand
import com.fabledt5.navigation.R
import com.fabledt5.navigation.directions.PrimaryAppDirections

sealed class BottomBarItem(
    val destination: NavigationCommand,
    val title: String,
    @DrawableRes val icon: Int
) {
    object Home : BottomBarItem(
        destination = PrimaryAppDirections.home,
        title = "Home",
        icon = R.drawable.ic_home
    )
    object Catalogue : BottomBarItem(
        destination = PrimaryAppDirections.catalogue,
        title = "Catalogue",
        icon = R.drawable.ic_catalogue
    )
    object Collections : BottomBarItem(
        destination = PrimaryAppDirections.collections,
        title = "Collections",
        icon = R.drawable.ic_collections
    )
    object Profile : BottomBarItem(
        destination = PrimaryAppDirections.profile,
        title = "Profile",
        icon = R.drawable.ic_profile
    )
}
