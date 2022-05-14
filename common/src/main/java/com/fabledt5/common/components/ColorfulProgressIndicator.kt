package com.fabledt5.common.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.fabledt5.common.theme.MediumLateBlue
import com.fabledt5.common.theme.Turquoise
import kotlin.concurrent.fixedRateTimer

@Composable
fun ColorfulProgressIndicator(modifier: Modifier = Modifier) {
    var targetColor by remember { mutableStateOf(Turquoise) }

    DisposableEffect(key1 = true) {
        val timer = fixedRateTimer(period = 1332) {
            targetColor = if (targetColor == Turquoise) MediumLateBlue else Turquoise
        }
        onDispose { timer.cancel() }
    }
    val indicatorColor by animateColorAsState(targetValue = targetColor)

    CircularProgressIndicator(modifier = modifier, color = indicatorColor)
}