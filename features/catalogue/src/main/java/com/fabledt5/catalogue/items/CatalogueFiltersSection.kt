package com.fabledt5.catalogue.items

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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
import com.fabledt5.common.theme.Proxima
import com.fabledt5.domain.model.DeveloperItem
import com.fabledt5.domain.model.GameGenre
import com.fabledt5.domain.model.Resource
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow

@ExperimentalFoundationApi
@Composable
fun CatalogueFiltersSection(
    developersFilters: Resource<List<DeveloperItem>>,
    genresFilters: Resource<List<GameGenre>>
) {
    Column(
        modifier = Modifier
            .padding(top = 20.dp, bottom = 75.dp)
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState())
    ) {
        when (developersFilters) {
            is Resource.Error -> ShowDevelopersError()
            is Resource.Success -> ShowDevelopersFilters(
                developersFilters.data,
                modifier = Modifier.fillMaxWidth()
            )
            else -> ShowDevelopersLoading()
        }
        Spacer(modifier = Modifier.height(30.dp))
        PlatformsFilter(
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(30.dp))
        when (genresFilters) {
            is Resource.Error -> ShowGenresError()
            is Resource.Success -> ShowGenresFilter(
                genresFilters.data,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .fillMaxWidth(),
                onFilterClicked = {

                })
            else -> ShowGenresLoading()
        }
    }
}

@Composable
fun ShowDevelopersError() {

}

@Composable
fun ShowDevelopersLoading() {

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
            key = { _, developer -> developer.developerName })
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
//                var isSelected by remember { mutableStateOf(false) }
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

@Composable
fun ShowGenresError() {

}

@Composable
fun ShowGenresLoading() {

}

@ExperimentalFoundationApi
@Composable
fun ShowGenresFilter(
    genresList: List<GameGenre>,
    modifier: Modifier = Modifier,
    onFilterClicked: (Int) -> Unit
) {
    var isListOpen by remember { mutableStateOf(false) }
    var listMaxSize by remember { mutableStateOf(9) }

    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .padding(bottom = 5.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.by_genre).uppercase(),
                modifier = Modifier.align(Alignment.BottomStart),
                color = Color.White,
                fontFamily = Mark,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            if (!isListOpen)
                Row(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .clickable {
                            if (!isListOpen) {
                                listMaxSize = Int.MAX_VALUE
                                isListOpen = !isListOpen
                            }
                        }
                ) {
                    Text(
                        text = stringResource(R.string.see_all),
                        modifier = Modifier.padding(end = 5.dp),
                        color = Color.White.copy(alpha = .4f),
                        fontFamily = Proxima,
                        fontSize = 14.sp
                    )
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = stringResource(R.string.ic_open_genres_list),
                        modifier = Modifier.size(14.dp),
                        tint = Color.White.copy(alpha = .4f)
                    )
                }
        }
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize(),
            mainAxisAlignment = FlowMainAxisAlignment.SpaceBetween
        ) {
            genresList.take(listMaxSize).forEach { item ->
                var isItemSelected by remember { mutableStateOf(false) }
                FilterTextItem(
                    filterName = item.genreTitle,
                    onItemClick = {
                        isItemSelected = !isItemSelected
                        onFilterClicked(item.id)
                    },
                    isActive = isItemSelected,
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth(fraction = .3f)
                )
            }
        }
    }
}