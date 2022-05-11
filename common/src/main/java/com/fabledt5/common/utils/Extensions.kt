package com.fabledt5.common.utils

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.graphicsLayer
import androidx.core.text.HtmlCompat
import com.fabledt5.common.theme.Background
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield

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

@ExperimentalPagerApi
fun Modifier.autoScroll(pagerState: PagerState, scrollRate: Long = 5000) = composed {
    LaunchedEffect(key1 = pagerState.currentPage) {
        yield()
        delay(timeMillis = scrollRate)
        pagerState.animateScrollToPage(
            if (pagerState.currentPage == pagerState.pageCount - 1) 0
            else pagerState.currentPage + 1
        )
    }
    this
}