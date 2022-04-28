package com.fabledt5.catalogue.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ScaleFactor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fabledt5.catalogue.R
import com.fabledt5.common.theme.DarkLateBlack
import com.fabledt5.common.theme.DefaultHorizontalGradient

@Composable
fun FilterImageItem(
    filterImage: Painter,
    isActive: Boolean,
    onItemSelected: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor by animateColorAsState(
        targetValue = if (isActive) Color.Black else DarkLateBlack
    )
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(size = 8.dp))
            .background(backgroundColor)
            .then(
                if (isActive) Modifier.border(
                    BorderStroke(
                        width = 1.dp,
                        brush = DefaultHorizontalGradient
                    ),
                    shape = RoundedCornerShape(size = 8.dp)
                ) else Modifier
            )
            .clickable { onItemSelected() },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = filterImage,
            contentDescription = stringResource(R.string.image_filter_item),
            modifier = Modifier.matchParentSize(),
            contentScale = object : ContentScale {
                override fun computeScaleFactor(srcSize: Size, dstSize: Size) =
                    ScaleFactor(scaleX = .1f, scaleY = .1f)
            },
            colorFilter = ColorFilter.tint(Color.White)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FilterImageItemPreview() {
    FilterImageItem(
        isActive = false,
        modifier = Modifier.size(150.dp),
        onItemSelected = {},
        filterImage = painterResource(id = R.drawable.logo_play)
    )
}