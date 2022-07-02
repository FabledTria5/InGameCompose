package com.fabledt5.common.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.fabledt5.common.R

val Typography = Typography(
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
)

val Mark = FontFamily(
    Font(R.font.mark_regular),
    Font(R.font.mark_bold, FontWeight.Bold)
)

val Proxima = FontFamily(
    Font(R.font.proxima_regular),
    Font(R.font.proxima_bold, FontWeight.Bold)
)