package com.fabledt5.home.pages

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.fabledt5.common.components.ColorfulProgressIndicator
import com.fabledt5.common.theme.PROGRESS_INDICATOR_REGULAR
import com.fabledt5.domain.model.ErrorItem
import com.fabledt5.domain.model.Resource
import com.fabledt5.domain.model.items.GameItem
import com.fabledt5.home.R
import com.fabledt5.home.components.GameCard
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment

@Composable
fun HomeGamesPage(pageName: String, games: Resource<List<GameItem>>, onGameClick: (Int) -> Unit) {
    when (games) {
        is Resource.Error -> ShowRecommendedGamesError(games.error)
        is Resource.Success -> if (games.data.isNotEmpty()) ShowRecommendedGames(
            gamesList = games.data,
            onGameClick = onGameClick
        ) else ShowEmptyGamesList(pageName)
        else -> ShowRecommendedGamesLoading()
    }
}

@Composable
fun ShowRecommendedGamesError(error: ErrorItem) {
    val context = LocalContext.current
    Toast.makeText(context, error.errorMessage, Toast.LENGTH_SHORT).show()

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = stringResource(R.string.games_error_message),
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ShowRecommendedGamesLoading() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        contentAlignment = Alignment.Center
    ) {
        ColorfulProgressIndicator(modifier = Modifier.size(PROGRESS_INDICATOR_REGULAR))
    }
}

@Composable
fun ShowEmptyGamesList(pageName: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = R.string.empty_games_list_message, pageName),
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ShowRecommendedGames(gamesList: List<GameItem>, onGameClick: (Int) -> Unit) {
    FlowRow(
        modifier = Modifier
            .navigationBarsPadding()
            .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
            .fillMaxWidth(),
        mainAxisAlignment = MainAxisAlignment.SpaceBetween,
        crossAxisSpacing = 10.dp,
    ) {
        gamesList.forEach { gameItem ->
            GameCard(game = gameItem, onGameClick = onGameClick)
        }
    }
}