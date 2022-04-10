package com.fabledt5.common.utils

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.graphicsLayer
import androidx.core.text.HtmlCompat
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

fun String.createFromHtml() = HtmlCompat.fromHtml(
    this,
    HtmlCompat.FROM_HTML_MODE_COMPACT
).toString()

fun Modifier.gradient(brush: Brush) = graphicsLayer(alpha = .99f)
    .drawWithCache {
        onDrawWithContent {
            drawContent()
            drawRect(brush = brush, blendMode = BlendMode.SrcAtop)
        }
    }