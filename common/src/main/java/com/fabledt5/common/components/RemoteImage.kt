package com.fabledt5.common.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.fabledt5.common.R

@Composable
fun RemoteImage(
    imagePath: String?,
    contentDescription: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale,
    colorFilter: ColorFilter? = null
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imagePath)
            .crossfade(durationMillis = 500)
            .error(R.drawable.error_placeholder)
            .build(),
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = contentScale,
        colorFilter = colorFilter
    )
}