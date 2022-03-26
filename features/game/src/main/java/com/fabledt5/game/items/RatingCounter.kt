package com.fabledt5.game.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.fabledt5.common.theme.*
import com.fabledt5.game.R

@Composable
fun RatingCounter(rating: Int, ratingsCount: Int?, ratingsPercent: Int) {
    var activeBoxWidth by remember { mutableStateOf(IntSize.Zero) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            repeat(times = rating) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_star_filled),
                    contentDescription = stringResource(id = R.string.icon_star),
                    modifier = Modifier.size(12.dp),
                    tint = SandyBrown
                )
            }
            repeat(times = 5 - rating) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_star_outlined),
                    contentDescription = stringResource(id = R.string.icon_star),
                    modifier = Modifier.size(12.dp),
                    tint = SandyBrown
                )
            }
        }
        Text(
            text = ratingsCount.toString(),
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .width(20.dp),
            color = Color.White,
            fontFamily = Proxima,
            textAlign = TextAlign.Center
        )
        Box {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(5.dp)
                    .clip(RoundedCornerShape(size = 5.dp))
                    .background(DimGray)
                    .onGloballyPositioned { activeBoxWidth = it.size }
            )
            Box(
                modifier = Modifier
                    .width(with(LocalDensity.current) {
                        activeBoxWidth.width.toDp() / 100 * ratingsPercent
                    })
                    .height(5.dp)
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
    }
}

@Preview(showBackground = true, backgroundColor = 0x000000)
@Composable
fun RatingCounterPreview() {
    RatingCounter(ratingsCount = 28, rating = 4, ratingsPercent = 42)
}