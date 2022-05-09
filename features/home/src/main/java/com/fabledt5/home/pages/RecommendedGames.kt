package com.fabledt5.home.pages

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.fabledt5.common.components.CoilImage
import com.fabledt5.common.theme.DimGray
import com.fabledt5.common.theme.MediumLateBlue
import com.fabledt5.domain.model.GameItem
import com.fabledt5.domain.model.Resource
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState

@ExperimentalFoundationApi
@ExperimentalPagerApi
@Composable
fun RecommendedGamesPager(
    gamesPagerState: PagerState,
    upcomingGames: Resource<List<GameItem>>,
    bestGames: Resource<List<GameItem>>,
    newGames: Resource<List<GameItem>>,
    onGameClick: (Int) -> Unit
) {
    HorizontalPager(
        count = 3,
        state = gamesPagerState,
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) { page ->
        when (page) {
            0 -> RecommendedGamesPage(
                upcomingGames,
                onGameClick = onGameClick
            )
            1 -> RecommendedGamesPage(
                bestGames,
                onGameClick = onGameClick
            )
            2 -> RecommendedGamesPage(
                newGames,
                onGameClick = onGameClick
            )
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun RecommendedGamesPage(games: Resource<List<GameItem>>, onGameClick: (Int) -> Unit) {
    when (games) {
        is Resource.Error -> ShowRecommendedGamesError()
        is Resource.Success -> ShowRecommendedGames(
            gamesList = games.data,
            onGameClick = onGameClick
        )
        else -> ShowRecommendedGamesLoading()
    }
}

@Composable
fun ShowRecommendedGamesError() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = "Error while receiving games",
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
        CircularProgressIndicator(modifier = Modifier.size(20.dp), color = MediumLateBlue)
    }
}

@ExperimentalFoundationApi
@Composable
fun ShowRecommendedGames(gamesList: List<GameItem>, onGameClick: (Int) -> Unit) {
    FlowRow(
        modifier = Modifier
            .navigationBarsPadding()
            .padding(start = 10.dp, top = 10.dp, end = 10.dp, bottom = 65.dp)
            .fillMaxWidth(),
        mainAxisAlignment = MainAxisAlignment.SpaceBetween,
        crossAxisSpacing = 10.dp
    ) {
        gamesList.forEach { gameItem ->
            GameCard(game = gameItem, onGameClick = onGameClick)
        }
    }
}

@Composable
fun GameCard(game: GameItem, modifier: Modifier = Modifier, onGameClick: (Int) -> Unit) {
    Card(
        modifier = modifier
            .height(100.dp)
            .fillMaxWidth(fraction = .49f)
            .clickable { onGameClick(game.gameId) },
        elevation = 10.dp,
        shape = RoundedCornerShape(size = 10.dp),
        backgroundColor = DimGray
    ) {
        CoilImage(
            imagePath = game.gamePoster,
            contentDescription = "${game.gameTitle} game title",
            contentScale = ContentScale.Crop
        )
    }
}