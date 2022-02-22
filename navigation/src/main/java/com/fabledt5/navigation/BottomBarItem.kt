package com.fabledt5.navigation

import androidx.annotation.DrawableRes
import com.fabledt5.navigation.directions.BottomNavigationDirections

sealed class BottomBarItem(
    val destination: NavigationCommand,
    val title: String,
    @DrawableRes val icon: Int
) {
    object Home : BottomBarItem(
        destination = BottomNavigationDirections.home,
        title = "Home",
        icon = R.drawable.ic_home
    )
    object Catalogue : BottomBarItem(
        destination = BottomNavigationDirections.catalogue,
        title = "Catalogue",
        icon = R.drawable.ic_catalogue
    )
    object Collections : BottomBarItem(
        destination = BottomNavigationDirections.collections,
        title = "Collections",
        icon = R.drawable.ic_collections
    )
    object Profile : BottomBarItem(
        destination = BottomNavigationDirections.profile,
        title = "Profile",
        icon = R.drawable.ic_profile
    )
}
