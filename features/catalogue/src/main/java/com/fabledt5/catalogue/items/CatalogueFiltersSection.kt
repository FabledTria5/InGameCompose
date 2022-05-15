package com.fabledt5.catalogue.items

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fabledt5.catalogue.R
import com.fabledt5.catalogue.components.FilterImageItem
import com.fabledt5.catalogue.components.FilterTextItem
import com.fabledt5.common.theme.Mark
import com.fabledt5.domain.model.DeveloperItem
import com.fabledt5.domain.model.Resource
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow

@ExperimentalFoundationApi
@Composable
fun CatalogueFiltersSection(developersFilters: Resource<List<DeveloperItem>>) {
    Column(
        modifier = Modifier
            .padding(vertical = 20.dp)
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState())
    ) {
        when (developersFilters) {
//            is Resource.Error -> showDevelopersError()
            is Resource.Success -> ShowDevelopersFilters(
                developersFilters.data,
                modifier = Modifier.fillMaxWidth()
            )
//            else -> showDevelopersLoading()
        }
        Spacer(modifier = Modifier.height(30.dp))
        PlatformsFilter(
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(30.dp))
        GenresFilter(
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun ShowDevelopersFilters(developersFilter: List<DeveloperItem>, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.by_developer).uppercase(),
            modifier = Modifier.padding(start = 10.dp, bottom = 15.dp),
            color = Color.White,
            fontFamily = Mark,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
    }
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 10.dp)
    ) {
        itemsIndexed(
            items = developersFilter,
            key = { _, developer -> developer.developerId })
        { index, developer ->
            var isSelected by remember { mutableStateOf(false) }
            FilterImageItem(
                filterImage = developer.icon,
                isActive = isSelected,
                onItemSelected = { isSelected = !isSelected },
                modifier = Modifier.size(90.dp)
            )
            if (index != developersFilter.lastIndex) Spacer(modifier = Modifier.width(10.dp))
        }
    }
}

@Composable
fun PlatformsFilter(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.by_platform).uppercase(),
            modifier = Modifier.padding(start = 10.dp, bottom = 15.dp),
            color = Color.White,
            fontFamily = Mark,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 10.dp)
        ) {
            items(5) {
                var isSelected by remember { mutableStateOf(false) }
//                FilterImageItem(
//                    filterImage = painterResource(id = R.drawable.logo_play),
//                    isActive = isSelected,
//                    onItemSelected = { isSelected = !isSelected },
//                    modifier = Modifier.size(90.dp),
//                )
                if (it < 5) Spacer(modifier = Modifier.width(10.dp))
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun GenresFilter(modifier: Modifier = Modifier) {
    val genresList = listOf(
        "Action",
        "Adventure",
        "Casual",
        "Indie",
        "Racing",
        "RPG",
        "Simulation",
        "Sports",
        "Strategy"
    )
    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.by_genre).uppercase(),
            modifier = Modifier.padding(start = 10.dp, bottom = 15.dp),
            color = Color.White,
            fontFamily = Mark,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        FlowRow(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .fillMaxWidth(),
            mainAxisAlignment = FlowMainAxisAlignment.SpaceBetween
        ) {
            genresList.forEach { item ->
                var isItemSelected by remember { mutableStateOf(false) }
                FilterTextItem(
                    filterName = item,
                    onItemClick = { isItemSelected = !isItemSelected },
                    isActive = isItemSelected,
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .fillMaxWidth(fraction = .3f)
                )
            }
        }
    }
}