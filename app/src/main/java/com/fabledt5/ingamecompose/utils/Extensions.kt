package com.fabledt5.ingamecompose.utils

import androidx.compose.ui.graphics.Color
import com.fabledt5.common.theme.MidNightBlack
import com.google.accompanist.systemuicontroller.SystemUiController

fun SystemUiController.setTransparentStatusBar(darkIcons: Boolean = false) = this.setStatusBarColor(
    color = Color.Transparent, darkIcons = darkIcons
)

fun SystemUiController.setPrimaryColor(darkIcons: Boolean = false) = this.setStatusBarColor(
    color = MidNightBlack, darkIcons = darkIcons
)