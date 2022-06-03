package com.fabledt5.catalogue.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.fabledt5.catalogue.R
import com.fabledt5.common.components.RemoteImage
import com.fabledt5.common.theme.DarkLateBlack
import com.fabledt5.common.theme.DefaultHorizontalGradient

@Composable
fun FilterImageItem(
    filterImage: String,
    isActive: Boolean,
    modifier: Modifier = Modifier,
    onItemSelected: () -> Unit,
) {
    val backgroundColor by animateColorAsState(
        targetValue = if (isActive) Color.Black else DarkLateBlack
    )
    val iconTint by animateColorAsState(
        targetValue = if (isActive) Color.White else Color.White.copy(alpha = .3f)
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
        RemoteImage(
            imagePath = filterImage,
            contentDescription = stringResource(R.string.image_filter_item),
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.None,
            colorFilter = ColorFilter.tint(iconTint)
        )
    }
}