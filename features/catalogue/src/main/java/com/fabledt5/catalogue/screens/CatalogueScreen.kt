package com.fabledt5.catalogue.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.collectAsLazyPagingItems
import com.fabledt5.catalogue.CatalogueViewModel
import com.fabledt5.catalogue.R
import com.fabledt5.catalogue.components.CatalogueSearchField
import com.fabledt5.catalogue.items.CatalogueFiltersSection
import com.fabledt5.catalogue.items.CatalogueSearchSection
import com.fabledt5.common.theme.Mark
import com.fabledt5.common.theme.MidNightBlack

@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalFoundationApi::class,
    ExperimentalComposeUiApi::class
)
@Composable
fun CatalogueScreen(catalogueViewModel: CatalogueViewModel) {
    val developersFilters = catalogueViewModel.developersList
    val genresFilters = catalogueViewModel.genresList
    val platformsFilters = catalogueViewModel.platformsList

    val selectedPlatforms = catalogueViewModel.selectedPlatforms
    val selectedGenres = catalogueViewModel.selectedGenres
    val selectedDevelopers = catalogueViewModel.selectedDevelopers

    val searchResults = catalogueViewModel.searchResults.collectAsLazyPagingItems()

    var isFiltersListOpen by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf(catalogueViewModel.searchQuery.value) }

    Scaffold(
        modifier = Modifier.statusBarsPadding(),
        topBar = {
            CatalogueTopBar(
                isFiltersOpen = isFiltersListOpen,
                onOpenFiltersClicked = { isFiltersListOpen = true },
                onSaveFiltersClicked = { isFiltersListOpen = false })
        },
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(top = contentPadding.calculateTopPadding())
                .fillMaxSize()
        ) {
            CatalogueSearchField(
                searchQuery = searchQuery,
                onSearchQueryChange = { searchQuery = it },
                onSearchClicked = {
                    catalogueViewModel.searchQuery.value = searchQuery
                },
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 20.dp)
                    .fillMaxWidth()
            )
            if (isFiltersListOpen) CatalogueFiltersSection(
                developersFilters = developersFilters,
                genresFilters = genresFilters,
                platformsFilters = platformsFilters,
                selectedGenres = selectedGenres,
                selectedPlatforms = selectedPlatforms,
                selectedDevelopers = selectedDevelopers,
                onPlatformClicked = {
                    catalogueViewModel.togglePlatform(it)
                },
                onGenreClicked = {
                    catalogueViewModel.toggleGenre(it)
                },
                onDeveloperClicked = {
                    catalogueViewModel.toggleDeveloper(it)
                }
            )
            else CatalogueSearchSection(
                searchResults = searchResults,
                onGameClicked = { gameId ->
                    catalogueViewModel.onGameClicked(gameId)
                })
        }
    }
}

@Composable
fun CatalogueTopBar(
    isFiltersOpen: Boolean,
    onOpenFiltersClicked: () -> Unit,
    onSaveFiltersClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(MidNightBlack)
            .padding(vertical = 15.dp)
    ) {
        Text(
            text = stringResource(R.string.catalogue).uppercase(),
            modifier = Modifier.align(Alignment.Center),
            color = Color.White,
            fontFamily = Mark,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        if (isFiltersOpen)
            TextButton(
                onClick = onSaveFiltersClicked,
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                Text(
                    text = stringResource(R.string.save).uppercase(),
                    color = Color.White.copy(alpha = .5f),
                    fontFamily = Mark,
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp
                )
            }
        else
            IconButton(
                onClick = onOpenFiltersClicked,
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = stringResource(R.string.icon_edit),
                    modifier = Modifier.size(20.dp),
                    tint = Color.White.copy(alpha = .5f)
                )
            }
    }
}