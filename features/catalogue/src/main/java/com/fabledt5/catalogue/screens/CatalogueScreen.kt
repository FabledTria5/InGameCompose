package com.fabledt5.catalogue.screens

import android.app.Activity
import android.content.Intent
import android.speech.RecognizerIntent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.collectAsLazyPagingItems
import com.fabledt5.catalogue.CatalogueViewModel
import com.fabledt5.catalogue.R
import com.fabledt5.catalogue.items.CatalogueFiltersSection
import com.fabledt5.catalogue.items.CatalogueSearchSection
import com.fabledt5.common.theme.DarkLateBlack
import com.fabledt5.common.theme.Mark
import com.fabledt5.common.theme.MidNightBlack
import com.fabledt5.common.theme.Turquoise
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalFoundationApi
@Composable
fun CatalogueScreen(catalogueViewModel: CatalogueViewModel) {
    val developersFilters by catalogueViewModel.developersList.collectAsState()
    val genresFilters by catalogueViewModel.genresList.collectAsState()
    val platformsFilters by catalogueViewModel.platformsList.collectAsState()

    val selectedPlatforms = catalogueViewModel.selectedPlatforms
    val selectedGenres = catalogueViewModel.selectedGenres
    val selectedDevelopers = catalogueViewModel.selectedDevelopers

    val searchQuery by catalogueViewModel.searchQuery.collectAsState()
    val searchResults = catalogueViewModel.searchResults.collectAsLazyPagingItems()

    var isFiltersListOpen by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.systemBarsPadding(),
        topBar = {
            CatalogueTopBar(
                isFiltersOpen = isFiltersListOpen,
                onOpenFiltersClicked = { isFiltersListOpen = true },
                onSaveFiltersClicked = { isFiltersListOpen = false })
        },
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize()
        ) {
            CatalogueSearchField(
                searchQuery = searchQuery,
                onSearchQueryChange = { query ->
                    catalogueViewModel.searchQuery.value = query
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
                },
                onGameLoadingError = { loadState ->
                    catalogueViewModel.onGamesLoadingError(loadState)
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

@Composable
fun CatalogueSearchField(
    searchQuery: String,
    modifier: Modifier = Modifier,
    onSearchQueryChange: (String) -> Unit
) {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            val spokenText = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)?.let {
                it[0]
            }
            if (!spokenText.isNullOrEmpty()) onSearchQueryChange(spokenText)
        }
    }

    OutlinedTextField(
        value = searchQuery,
        onValueChange = onSearchQueryChange,
        modifier = modifier.shadow(elevation = 10.dp),
        trailingIcon = {
            IconButton(onClick = {
                Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
                    putExtra(
                        RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
                    )
                    putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US")
                }.let(launcher::launch)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_voice),
                    contentDescription = stringResource(R.string.icon_voice)
                )
            }
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.White,
            containerColor = DarkLateBlack,
            cursorColor = Turquoise,
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            disabledBorderColor = Color.Transparent,
            focusedLeadingIconColor = Color.White.copy(alpha = .2f),
            unfocusedLeadingIconColor = Color.White.copy(alpha = .2f),
            focusedTrailingIconColor = Color.White.copy(alpha = .4f),
            unfocusedTrailingIconColor = Color.White.copy(alpha = .4f)
        ),
        keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences)
    )
}