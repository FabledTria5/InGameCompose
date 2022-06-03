package com.fabledt5.common.components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.clearAndSetSemantics

@Composable
fun ExposedDropDownMenuIcon(
    isExpanded: Boolean,
    onIconClick: () -> Unit,
    contentDescription: String,
    iconDrawable: ImageVector
) {
    IconButton(
        onClick = onIconClick,
        modifier = Modifier.clearAndSetSemantics { }
    ) {
        Icon(
            imageVector = iconDrawable,
            contentDescription = contentDescription,
            modifier = Modifier.rotate(
                if (isExpanded) 180f else 360f
            )
        )
    }
}