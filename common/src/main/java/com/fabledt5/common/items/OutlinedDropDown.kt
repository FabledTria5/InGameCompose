package com.fabledt5.common.items

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.toSize
import com.fabledt5.common.R

@ExperimentalMaterialApi
@Composable
fun OutlinedDropDown(modifier: Modifier = Modifier) {
    val itemsList = listOf("Playstation 5", "XBox Series X/S", "Nintendo Switch", "PC")

    var isExpanded by remember { mutableStateOf(false) }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    var selectedText by remember { mutableStateOf(itemsList.first()) }

    val icon = if (isExpanded) Icons.Default.KeyboardArrowUp
    else Icons.Default.KeyboardArrowDown

    Column(modifier = modifier) {
        OutlinedTextField(
            value = selectedText,
            onValueChange = {},
            modifier = modifier.onGloballyPositioned { coordinates ->
                textFieldSize = coordinates.size.toSize()
            },
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { isExpanded = !isExpanded }) {
                    Icon(
                        imageVector = icon,
                        contentDescription = stringResource(id = R.string.icon_arrow),
                        tint = Color.White
                    )
                }
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color.Transparent,
                focusedBorderColor = Color.White.copy(alpha = .7f),
                unfocusedBorderColor = Color.White.copy(alpha = .4f),
                disabledBorderColor = Color.White.copy(alpha = .4f),
                textColor = Color.White
            )
        )
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false },
            modifier = Modifier.width(with(LocalDensity.current) {
                textFieldSize.width.toDp()
            })
        ) {
            itemsList.forEach { platform ->
                DropdownMenuItem(onClick = { selectedText = platform }) {
                    Text(text = platform, color = Color.White)
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun OutlinedDropDownPreview() {
    OutlinedDropDown()
}