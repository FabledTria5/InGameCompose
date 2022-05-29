package com.fabledt5.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.toSize
import com.fabledt5.common.components.ExposedDropDownMenuIcon
import com.fabledt5.common.theme.Background
import com.fabledt5.domain.model.PlatformItem
import com.fabledt5.domain.model.Resource
import com.fabledt5.home.R

@ExperimentalMaterial3Api
@Composable
fun OutlinedDropDown(
    modifier: Modifier = Modifier,
    itemsList: List<PlatformItem>,
    selectedItem: Resource<PlatformItem>,
    onItemSelected: (Int) -> Unit
) {
    var platformsItemSize by remember { mutableStateOf(IntSize.Zero) }

    var isExpanded by remember { mutableStateOf(false) }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    Column(modifier = modifier) {
        when (selectedItem) {
            is Resource.Error -> onItemSelected(itemsList.first().platformId)
            is Resource.Success -> ExposedDropdownMenuBox(
                expanded = isExpanded,
                onExpandedChange = { isExpanded = !isExpanded }
            ) {
                OutlinedTextField(
                    value = selectedItem.data.platformName,
                    onValueChange = {},
                    modifier = modifier.onGloballyPositioned { coordinates ->
                        textFieldSize = coordinates.size.toSize()
                    },
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropDownMenuIcon(
                            isExpanded = isExpanded,
                            onIconClick = { isExpanded = !isExpanded },
                            contentDescription = stringResource(id = R.string.icon_arrow),
                            iconDrawable = Icons.Default.KeyboardArrowDown
                        )
                    },
                    colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(
                        containerColor = Color.Transparent,
                        focusedBorderColor = Color.White.copy(alpha = .7f),
                        unfocusedBorderColor = Color.White.copy(alpha = .4f),
                        disabledBorderColor = Color.White.copy(alpha = .4f),
                        textColor = Color.White
                    )
                )
            }
            else -> Unit
        }
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) {
                    textFieldSize.width.toDp()
                })
                .height(with(LocalDensity.current) { (platformsItemSize.height + 10).toDp() * 3 })
                .background(Background)
        ) {
            itemsList.forEach { platform ->
                DropdownMenuItem(
                    onClick = {
                        onItemSelected(platform.platformId)
                        isExpanded = false
                    },
                    modifier = Modifier.onGloballyPositioned {
                        platformsItemSize = it.size
                    },
                    text = { Text(text = platform.platformName, color = Color.White) }
                )
            }
        }
    }
}