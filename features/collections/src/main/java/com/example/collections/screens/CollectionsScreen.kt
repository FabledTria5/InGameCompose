package com.example.collections.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.collections.CollectionsViewModel
import com.example.collections.R
import com.example.collections.pages.CalendarPage
import com.example.collections.pages.FavouriteGamesPage
import com.example.collections.pages.PlayedGamesPage
import com.fabledt5.common.components.OutlinedTabs
import com.fabledt5.common.theme.Mark
import com.fabledt5.common.theme.MidNightBlack
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(
    ExperimentalPagerApi::class,
    ExperimentalMaterial3Api::class,
    ExperimentalFoundationApi::class,
    ExperimentalMaterialApi::class,
)
@Composable
fun CollectionsScreen(collectionsViewModel: CollectionsViewModel) {
    val collectionsTabs = stringArrayResource(id = R.array.collections_tabs)
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    val calendarGames = collectionsViewModel.calendarGamesMap
    val favoriteGames by collectionsViewModel.favoriteGames.collectAsState()
    val playedGames by collectionsViewModel.playedGames.collectAsState()

    Scaffold(modifier = Modifier.statusBarsPadding(), topBar = {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(MidNightBlack)
                .padding(vertical = 15.dp)
        ) {
            Text(
                text = stringResource(R.string.collections).uppercase(),
                modifier = Modifier.align(Alignment.Center),
                color = Color.White,
                fontFamily = Mark,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
    }) { paddingValues ->
        Column(modifier = Modifier.padding(top = paddingValues.calculateTopPadding())) {
            OutlinedTabs(
                pagerState = pagerState,
                tabsTitles = collectionsTabs,
                onTabSelected = { padeIndex ->
                    scope.launch {
                        pagerState.animateScrollToPage(padeIndex)
                    }
                },
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 20.dp)
                    .fillMaxWidth(),
            )
            HorizontalPager(
                count = collectionsTabs.size,
                modifier = Modifier.fillMaxWidth(),
                state = pagerState,
                verticalAlignment = Alignment.Top,
                userScrollEnabled = false
            ) { page ->
                when (page) {
                    0 -> {
                        FavouriteGamesPage(
                            favoriteGames = favoriteGames,
                            onGameClicked = { gameId ->
                                collectionsViewModel.gameClicked(gameId)
                            })
                    }
                    1 -> {
                        PlayedGamesPage(
                            playedGames = playedGames,
                            onAddToFavoriteClicked = { game ->
                                collectionsViewModel.addGameToFavorites(game)
                            },
                            onGameClicked = { gameId ->
                                collectionsViewModel.gameClicked(gameId)
                            })
                    }
                    2 -> {
                        CalendarPage(
                            calendarGames = calendarGames,
                            onDateSelected = { localDate ->
                                collectionsViewModel.dateSelected(localDate)
                            },
                            onGameClicked = { gameId ->
                                collectionsViewModel.gameClicked(gameId)
                            },
                            onAddToPlayedClicked = { game ->
                                collectionsViewModel.addGameToPlayed(game)
                            })
                    }
                }
            }
        }
    }
}