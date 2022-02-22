package com.fabledt5.common.utils

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import com.fabledt5.common.theme.Background

fun ContentDrawScope.drawImageForeground() {
    drawRect(
        brush = Brush.verticalGradient(
            colors = listOf(
                Color.Transparent,
                Background
            )
        )
    )
}