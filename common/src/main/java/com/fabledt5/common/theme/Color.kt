package com.fabledt5.common.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val Turquoise = Color(0xFF2dE1F3)
val MediumLateBlue = Color(0xFF747EFB)
val SandyBrown = Color(0xFFf5CD4E)
val Background = Color(0xFF121214)
val DarkLateGray = Color(0xFF2B2C2F)
val DimGray = Color(0xFF656467)
val MidNightBlack = Color(0xFF191A1D)
val DarkLateBlack = Color(0xFF222224)

val DefaultHorizontalGradient = Brush.horizontalGradient(colors = listOf(Turquoise, MediumLateBlue))

@OptIn(ExperimentalTextApi::class)
fun GradinentTextStyle(isEnabled: Boolean = true): TextStyle = TextStyle(
    brush = if (isEnabled) DefaultHorizontalGradient else null
)