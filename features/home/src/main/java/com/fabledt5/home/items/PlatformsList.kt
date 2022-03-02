package com.fabledt5.home.items

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fabledt5.common.items.OutlinedDropDown
import com.fabledt5.common.theme.Mark
import com.fabledt5.common.theme.MediumLateBlue
import com.fabledt5.domain.model.PlatformItem
import com.fabledt5.domain.model.Resource
import com.fabledt5.home.R

@ExperimentalMaterialApi
@Composable
fun PlatformsList(
    platformsList: Resource<List<PlatformItem>>,
    favoritePlatform: PlatformItem,
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
        if (platformsList is Resource.Success && favoritePlatform.platformName.isNotEmpty())
            OutlinedDropDown(
                itemsList = platformsList.data.map { it.platformName },
                selectedItem = favoritePlatform.platformName,
                onItemSelected = { platformName ->
                    val selectedPlatform =
                        platformsList.data.first { it.platformName == platformName }
                    onPlatformSelected(selectedPlatform.platformId)
                }
            )
        else
            CircularProgressIndicator(modifier = Modifier.size(20.dp), color = MediumLateBlue)
    }
}