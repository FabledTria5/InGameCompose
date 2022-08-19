package com.fabledt5.catalogue.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fabledt5.common.theme.DarkLateBlack
import com.fabledt5.common.theme.GradinentTextStyle
import com.fabledt5.common.theme.Mark

@Composable
fun FilterTextItem(
    filterName: String,
    modifier: Modifier = Modifier,
    onItemClick: () -> Unit,
    isActive: Boolean
) {
    val backgroundColor by animateColorAsState(
        targetValue = if (isActive) Color.Black else DarkLateBlack
    )
    val borderColor by animateColorAsState(
        targetValue = if (isActive) Color.White else Color.DarkGray
    )
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(size = 8.dp))
            .background(backgroundColor)
            .then(
                if (isActive) Modifier.border(
                    border = BorderStroke(
                        width = 1.dp,
                        color = borderColor
                    ),
                    shape = RoundedCornerShape(size = 8.dp)
                ) else Modifier
            )
            .clickable { onItemClick() },
    ) {
        Text(
            text = filterName.uppercase(),
            modifier = Modifier
                .padding(vertical = 15.dp)
                .align(Alignment.Center),
            color = if (!isActive) Color.White.copy(alpha = .3f) else Color.White,
            fontFamily = Mark,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            style = GradinentTextStyle(isEnabled = isActive)
        )
    }
}