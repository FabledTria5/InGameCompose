package com.fabledt5.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fabledt5.common.components.OutlinedDropDown
import com.fabledt5.common.theme.Mark
import com.fabledt5.domain.model.PlatformItem
import com.fabledt5.domain.model.Resource
import com.fabledt5.home.R

@Composable
fun PlatformsList(
    platformsList: Resource<List<PlatformItem>>,
    favoritePlatform: Resource<PlatformItem>,
    onPlatformSelected: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.for_platform).uppercase(),
            modifier = Modifier.padding(end = 20.dp),
            color = Color.White.copy(alpha = .9f),
            fontWeight = FontWeight.Bold,
            fontFamily = Mark,
            fontSize = 17.sp
        )
        when (platformsList) {
            is Resource.Error -> ShowPlatformsError()
            is Resource.Success -> ShowPlatformsList(
                platformsList.data,
                favoritePlatform,
                onPlatformSelected = onPlatformSelected
            )
            else -> ShowPlatformsLoading()
        }
    }
}

@Composable
fun ShowPlatformsLoading() {

}

@Composable
fun ShowPlatformsError() {

}

@Composable
fun ShowPlatformsList(
    platformsList: List<PlatformItem>,
    favoritePlatform: Resource<PlatformItem>,
    onPlatformSelected: (Int) -> Unit
) {
    OutlinedDropDown(
        itemsList = platformsList,
        selectedItem = favoritePlatform,
        onItemSelected = onPlatformSelected
    )
}
