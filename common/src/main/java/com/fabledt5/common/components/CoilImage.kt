package com.fabledt5.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberImagePainter
import com.fabledt5.common.R
import com.fabledt5.common.theme.SandyBrown

@Composable
fun CoilImage(
    imagePath: String?,
    contentDescription: String,
    modifier: Modifier = Modifier,
    scaleType: ContentScale,
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
        contentScale = scaleType
    )
}