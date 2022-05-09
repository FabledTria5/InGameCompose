package com.fabledt5.common.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberImagePainter
import com.fabledt5.common.R

@Composable
fun CoilImage(
    imagePath: String?,
    contentDescription: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale,
    colorFilter: ColorFilter? = null
) {
    val coilPainter = rememberImagePainter(
        data = imagePath,
        builder = {
            error(R.drawable.error_placeholder)
            crossfade(durationMillis = 500)
        }
    )
    Image(
        painter = coilPainter,
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = contentScale,
        colorFilter = colorFilter
    )
}