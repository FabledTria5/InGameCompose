package com.fabledt5.catalogue.items

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.fabledt5.catalogue.R
import com.fabledt5.catalogue.utils.isError
import com.fabledt5.catalogue.utils.isIdle
import com.fabledt5.catalogue.utils.isLoading
import com.fabledt5.catalogue.utils.isSuccess
import com.fabledt5.common.components.ColorfulProgressIndicator
import com.fabledt5.common.components.SharedGameItem
import com.fabledt5.common.theme.Mark
import com.fabledt5.common.theme.PROGRESS_INDICATOR_REGULAR
import com.fabledt5.domain.model.items.GameItem

@Composable
fun CatalogueSearchSection(
    searchResults: LazyPagingItems<GameItem>,
    onGameClicked: (Int) -> Unit,
) {
    when {
        searchResults.isIdle() -> ShowSearchSuggestions()
        searchResults.isLoading() -> ShowSearchLoading(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = .5f)
        )
        searchResults.isSuccess() -> ShowSearchResults(
            searchResults = searchResults,
            onGameClicked = onGameClicked
        )
        searchResults.isError() -> ShowSearchError(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = .5f)
        )
    }
}

@Composable
fun ShowSearchLoading(modifier: Modifier = Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        ColorfulProgressIndicator(modifier = Modifier.size(PROGRESS_INDICATOR_REGULAR))
    }
}

@Composable
fun ShowSearchError(modifier: Modifier = Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Text(
            text = stringResource(R.string.games_search_message),
            color = Color.White,
            fontFamily = Mark,
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp
        )
    }
}

@Composable
fun ShowSearchSuggestions() {
    val suggestionsList = stringArrayResource(id = R.array.search_suggestions)
    LazyColumn(modifier = Modifier.fillMaxWidth(), contentPadding = PaddingValues(top = 15.dp)) {
        items(suggestionsList) { suggestion ->
            Box(
                modifier = Modifier
                    .padding(vertical = 15.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = suggestion.uppercase(),
                    color = Color.White.copy(alpha = .2f),
                    fontFamily = Mark,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
        }
    }
}

@Composable
fun ShowSearchResults(
    searchResults: LazyPagingItems<GameItem>,
    onGameClicked: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 10.dp, top = 10.dp, end = 10.dp)
    ) {
        items(searchResults, key = { it.gameId }) { gameItem ->
            gameItem?.let {
                SharedGameItem(
                    gameItem = gameItem,
                    onGameClicked = onGameClicked
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}