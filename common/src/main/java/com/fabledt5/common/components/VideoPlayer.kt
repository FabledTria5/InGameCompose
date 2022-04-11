package com.fabledt5.common.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.fabledt5.common.databinding.CustomExoPlayerBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout

@Composable
fun VideoPlayer(url: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current

    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            val mediaItem = MediaItem.Builder().setUri(url).build()
            setMediaItem(mediaItem)
            prepare()
        }
    }

    DisposableEffect(
        AndroidViewBinding(factory = CustomExoPlayerBinding::inflate, modifier = modifier) {
            this.exoPlayer.apply {
                hideController()
                useController = true
                resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL
                player = exoPlayer
            }
        }
    ) {
        onDispose {
            exoPlayer.pause()
            exoPlayer.release()
        }
    }
}