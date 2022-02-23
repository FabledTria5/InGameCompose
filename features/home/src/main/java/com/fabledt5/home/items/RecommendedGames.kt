package com.fabledt5.home.items

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fabledt5.common.items.CoilImage
import com.fabledt5.common.theme.DarkLateGray
import com.fabledt5.common.theme.Turquoise
import com.fabledt5.domain.model.GameItem
import com.fabledt5.home.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun RecommendedGamesTabs(gamesPagerState: PagerState, onTabSelected: (Int) -> Unit) {
    val gamesTabs = stringArrayResource(id = R.array.home_screen_tabs)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 10.dp)
            .border(
                width = 1.dp,
                color = Color.White.copy(alpha = .3f),
                shape = RoundedCornerShape(10.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        TabRow(
            selectedTabIndex = gamesPagerState.currentPage,
            modifier = Modifier.height(50.dp),
            backgroundColor = Color.Transparent,
            indicator = { TabRowDefaults.Indicator(color = Color.Transparent) },
            divider = { TabRowDefaults.Divider(color = Color.Transparent) }
        ) {
            gamesTabs.forEachIndexed { index, tabName ->
                Tab(
                    selected = gamesPagerState.currentPage == index,
                    onClick = { onTabSelected(index) },
                    modifier = Modifier
                        .background(
                            color = if (gamesPagerState.currentPage == index)
                                Color(0xFF0c0d0e)
                            else
                                Color.Transparent,
                            shape = RoundedCornerShape(size = 10.dp)
                        )
                        .border(
                            border = if (gamesPagerState.currentPage == index) BorderStroke(
                                width = 1.dp,
                                color = Color.White
                            ) else BorderStroke(width = 0.dp, color = Color.Transparent),
                            shape = RoundedCornerShape(size = 10.dp)
                        ),
                    text = {
                        Text(
                            text = tabName.uppercase(),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    selectedContentColor = Turquoise,
                    unselectedContentColor = Color.White.copy(alpha = .5f)
                )
            }
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalPagerApi
@Composable
fun RecommendedGamesPager(gamesPagerState: PagerState, onGameClick: (Int) -> Unit) {
    HorizontalPager(
        count = 3,
        state = gamesPagerState,
        modifier = Modifier.fillMaxWidth()
    ) { page ->
        when (page) {
            0 -> RecommendedGamesPage(
                gamesList = listOf(
                    GameItem(gameId = 0),
                    GameItem(gameId = 1),
                    GameItem(gameId = 2),
                    GameItem(gameId = 3)
                ),
                onGameClick = onGameClick
            )
            1 -> RecommendedGamesPage(
                gamesList = listOf(
                    GameItem(gameId = 0),
                    GameItem(gameId = 1),
                    GameItem(gameId = 2),
                    GameItem(gameId = 3)
                ),
                onGameClick = onGameClick
            )
            2 -> RecommendedGamesPage(
                gamesList = listOf(
                    GameItem(gameId = 0),
                    GameItem(gameId = 1),
                    GameItem(gameId = 2),
                    GameItem(gameId = 3)
                ),
                onGameClick = onGameClick
            )
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun RecommendedGamesPage(gamesList: List<GameItem>, onGameClick: (Int) -> Unit) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(count = 2),
        contentPadding = PaddingValues(horizontal = 10.dp),
        userScrollEnabled = false
    ) {
        itemsIndexed(items = gamesList, key = { _, item -> item.gameId }) { index, game ->
            GameCard(
                game = game,
                modifier = Modifier.padding(
                    if (index.mod(2) == 0)
                        PaddingValues(end = 5.dp, top = 10.dp)
                    else
                        PaddingValues(start = 5.dp, top = 10.dp)
                ),
                onGameClick = onGameClick
            )
        }
    }
}

@Composable
fun GameCard(game: GameItem, modifier: Modifier = Modifier, onGameClick: (Int) -> Unit) {
    Card(
        modifier = modifier
            .height(100.dp)
            .clickable { onGameClick(game.gameId) },
        elevation = 10.dp,
        shape = RoundedCornerShape(size = 10.dp),
        backgroundColor = DarkLateGray
    ) {
        CoilImage(
            imagePath = game.gamePoster,
            contentDescription = "${game.gameTitle} game title",
            scaleType = ContentScale.Crop
        )
    }
}