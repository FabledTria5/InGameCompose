package com.fabledt5.game.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.fabledt5.common.theme.DimGray
import com.fabledt5.common.theme.MediumLateBlue
import com.fabledt5.common.theme.Turquoise

@Composable
fun RatingCounter() {
    var activeBoxWidth by remember { mutableStateOf(IntSize.Zero) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(10.dp)
            .clip(RoundedCornerShape(size = 5.dp))
            .background(DimGray)
            .onGloballyPositioned { activeBoxWidth = it.size }
    )
    Box(
        modifier = Modifier
            .width(with(LocalDensity.current) { activeBoxWidth.width.toDp() / 2 })
            .height(10.dp)
            .clip(RoundedCornerShape(topStart = 5.dp, bottomStart = 5.dp))
            .clip(CutCornerShape(bottomEnd = 20.dp))
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Turquoise,
                        MediumLateBlue
                    )
                )
            )
    )
}

@Preview(showBackground = true, backgroundColor = 0x000000)
@Composable
fun RatingCounterPreview() {
    RatingCounter()
}