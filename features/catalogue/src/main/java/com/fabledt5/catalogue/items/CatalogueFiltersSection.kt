package com.fabledt5.catalogue.items

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fabledt5.catalogue.R
import com.fabledt5.catalogue.components.FilterImageItem
import com.fabledt5.catalogue.components.FilterTextItem
import com.fabledt5.common.theme.Mark

@ExperimentalFoundationApi
@Composable
fun CatalogueFiltersSection() {
    Column(
        modifier = Modifier
            .padding(vertical = 20.dp)
            .fillMaxSize()
    ) {
        DevelopersFilter(
            modifier = Modifier.fillMaxWidth()
        )
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
fun DevelopersFilter(modifier: Modifier = Modifier) {
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
        items(5) {
            var isSelected by remember { mutableStateOf(false) }
            FilterImageItem(
                filterImage = painterResource(id = R.drawable.logo_ubi),
                isActive = isSelected,
                onItemSelected = { isSelected = !isSelected },
                modifier = Modifier.size(90.dp),
            )
            if (it < 5) Spacer(modifier = Modifier.width(10.dp))
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
                FilterImageItem(
                    filterImage = painterResource(id = R.drawable.logo_play),
                    isActive = isSelected,
                    onItemSelected = { isSelected = !isSelected },
                    modifier = Modifier.size(90.dp),
                )
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
        LazyVerticalGrid(
            cells = GridCells.Fixed(count = 3),
            contentPadding = PaddingValues(horizontal = 10.dp),
        ) {
            itemsIndexed(genresList) { index, item ->
                var isSelected by remember { mutableStateOf(false) }
                FilterTextItem(
                    filterName = item,
                    onItemClick = { isSelected = !isSelected },
                    isActive = isSelected,
                    modifier = Modifier
                        .padding(
                            end = if (index.mod(3) == 0) 5.dp else 0.dp,
                            start = if ((index + 1).mod(3) == 0) 5.dp else 0.dp,
                            bottom = 10.dp
                        )
                        .fillMaxWidth(fraction = .3f)
                )
            }
        }
    }
}