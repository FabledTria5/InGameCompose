package com.fabledt5.common.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.request.ImageRequest
import com.fabledt5.common.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun RemoteImage(
    imagePath: String?,
    contentDescription: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale,
    colorFilter: ColorFilter? = null,
    onSuccess: () -> Unit = {},
    onError: () -> Unit = {}
) {
    val scope = rememberCoroutineScope()
    val crossfadeDuration = 500

    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imagePath)
            .crossfade(durationMillis = crossfadeDuration)
            .error(R.drawable.error_placeholder)
            .build(),
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = contentScale,
        colorFilter = colorFilter,
        onState = { state ->
            if (state is AsyncImagePainter.State.Success) scope.launch {
                delay(timeMillis = crossfadeDuration + 100L)
                onSuccess()
            }
        }
    )
}