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
import com.fabledt5.domain.model.items.DeveloperItem
import com.fabledt5.domain.model.items.GenreItem
import com.fabledt5.domain.model.items.PlatformItem
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow

@ExperimentalFoundationApi
@Composable
fun CatalogueFiltersSection(
    developersFilters: List<DeveloperItem>,
    genresFilters: List<GenreItem>,
    platformsFilters: List<PlatformItem>,
    selectedGenres: List<Int>,
    selectedPlatforms: List<Int>,
    selectedDevelopers: List<String>,
    onPlatformClicked: (Int) -> Unit,
    onGenreClicked: (Int) -> Unit,
    onDeveloperClicked: (String) -> Unit
) {
    ShowFiltersSuccess(
        developersFilters,
        genresFilters,
        platformsFilters,
        selectedGenres,
        selectedPlatforms,
        selectedDevelopers,
        onDeveloperClicked = onDeveloperClicked,
        onGenreClicked = onGenreClicked,
        onPlatformClicked = onPlatformClicked
    )
}

@ExperimentalFoundationApi
@Composable
fun ShowFiltersSuccess(
    developersList: List<DeveloperItem>,
    genresList: List<GenreItem>,
    platformsList: List<PlatformItem>,
    selectedGenres: List<Int>,
    selectedPlatforms: List<Int>,
    selectedDevelopers: List<String>,
    onDeveloperClicked: (String) -> Unit,
    onGenreClicked: (Int) -> Unit,
    onPlatformClicked: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(top = 20.dp, bottom = 70.dp)
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState())
    ) {
        ShowDevelopersFilters(
            developersList = developersList,
            selectedDevelopers = selectedDevelopers,
            onDeveloperClicked = onDeveloperClicked,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(30.dp))
        ShowPlatformsFilter(
            platformsList = platformsList,
            selectedPlatforms = selectedPlatforms,
            onPlatformClicked = onPlatformClicked,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(30.dp))
        ShowGenresFilter(
            genresList = genresList,
            selectedGenres = selectedGenres,
            onGenreClicked = onGenreClicked,
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
fun ShowDevelopersFilters(
    developersList: List<DeveloperItem>,
    selectedDevelopers: List<String>,
    modifier: Modifier = Modifier,
    onDeveloperClicked: (String) -> Unit
) {
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
            items = developersList,
            key = { _, developer -> developer.developerName })
        { index, developer ->
            FilterImageItem(
                filterImage = developer.icon,
                isActive = selectedDevelopers.contains(developer.developerName),
                onItemSelected = {
                    onDeveloperClicked(developer.developerName)
                },
                modifier = Modifier.size(90.dp)
            )
            if (index != developersList.lastIndex) Spacer(modifier = Modifier.width(10.dp))
        }
    }
}

@Composable
fun ShowPlatformsFilter(
    platformsList: List<PlatformItem>,
    selectedPlatforms: List<Int>,
    modifier: Modifier = Modifier,
    onPlatformClicked: (Int) -> Unit
) {
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
            itemsIndexed(
                items = platformsList,
                key = { _, platform -> platform.platformId }) { index, platform ->
                FilterImageItem(
                    filterImage = platform.platformImage,
                    isActive = selectedPlatforms.contains(platform.platformId),
                    onItemSelected = {
                        onPlatformClicked(platform.platformId)
                    },
                    modifier = Modifier.size(90.dp),
                )
                if (index != platformsList.lastIndex) Spacer(modifier = Modifier.width(10.dp))
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun ShowGenresFilter(
    genresList: List<GenreItem>,
    selectedGenres: List<Int>,
    modifier: Modifier = Modifier,
    onGenreClicked: (Int) -> Unit
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
                .padding(bottom = 5.dp)
                .fillMaxWidth()
                .animateContentSize()
                .padding(),
            mainAxisAlignment = FlowMainAxisAlignment.SpaceBetween
        ) {
            genresList.take(listMaxSize).forEach { item ->
                FilterTextItem(
                    filterName = item.genreTitle,
                    onItemClick = {
                        onGenreClicked(item.genreId)
                    },
                    isActive = selectedGenres.contains(item.genreId),
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth(fraction = .3f)
                )
            }
        }
    }
}