package com.fabledt5.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import com.fabledt5.common.theme.DefaultHorizontalGradient
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState

@ExperimentalPagerApi
@Composable
fun GradientPagerIndicators(
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    activeColor: Brush = DefaultHorizontalGradient,
    inactiveColor: Color,
    indicatorWidth: Dp,
    indicatorHeight: Dp = indicatorWidth,
    spacing: Dp = indicatorWidth,
    indicatorShape: Shape = CircleShape
) {

    val indicatorWidthPx = LocalDensity.current.run { indicatorWidth.roundToPx() }
    val spacingPx = LocalDensity.current.run { spacing.roundToPx() }

    Box(modifier = modifier, contentAlignment = Alignment.CenterStart) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(spacing),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val indicatorModifier = Modifier
                .size(width = indicatorWidth, height = indicatorHeight)
                .background(color = inactiveColor, shape = indicatorShape)

            repeat(pagerState.pageCount) {
                Box(modifier = indicatorModifier)
            }
        }

        Box(modifier = Modifier
            .offset {
                val scrollPosition = (pagerState.currentPage + pagerState.currentPageOffset)
                    .coerceIn(0f,
                        (pagerState.pageCount - 1)
                            .coerceAtLeast(0)
                            .toFloat()
                    )
                IntOffset(
                    x = ((spacingPx + indicatorWidthPx) * scrollPosition).toInt(),
                    y = 0
                )
            }
            .size(width = indicatorWidth, height = indicatorHeight)
            .background(brush = activeColor, shape = indicatorShape))
    }
}