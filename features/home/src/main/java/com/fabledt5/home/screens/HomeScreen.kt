package com.fabledt5.home.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.unit.dp
import com.fabledt5.common.components.OutlinedTabs
import com.fabledt5.home.HomeViewModel
import com.fabledt5.home.R
import com.fabledt5.home.components.PlatformsList
import com.fabledt5.home.items.HotGames
import com.fabledt5.home.pages.HomeGamesPage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(
    ExperimentalPagerApi::class,
    ExperimentalMaterial3Api::class,
    ExperimentalFoundationApi::class
)
@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {
    val scope = rememberCoroutineScope()
    val gamesPagerState = rememberPagerState(initialPage = 0)
    val scrollState = rememberScrollState()
    val recommendedGamesTabs = stringArrayResource(id = R.array.home_screen_tabs)

    val hotGamesList by homeViewModel.hotGamesList.collectAsState()
    val platformsList by homeViewModel.platformsList.collectAsState()
    val favoritePlatform by homeViewModel.favoritePlatform.collectAsState()

    val upcomingGames by homeViewModel.upcomingGames.collectAsState()
    val bestGames by homeViewModel.bestGames.collectAsState()
    val newGames by homeViewModel.newGames.collectAsState()

    val onGameClick: (Int) -> Unit = { homeViewModel.openGameScreen(gameId = it) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        HotGames(
            hotGames = hotGamesList,
            onGameClicked = { homeViewModel.openGameScreen(gameId = it) })
        OutlinedTabs(
            pagerState = gamesPagerState,
            tabsTitles = recommendedGamesTabs,
            onTabSelected = { index ->
                scope.launch { gamesPagerState.scrollToPage(index) }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 10.dp)
        )
        PlatformsList(
            platformsList = platformsList,
            favoritePlatform = favoritePlatform,
            onPlatformSelected = { platformId ->
                homeViewModel.changeFavoritePlatform(platformId = platformId)
            }
        )
        HorizontalPager(
            count = recommendedGamesTabs.size,
            state = gamesPagerState,
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) { page ->
            when (page) {
                0 -> HomeGamesPage(
                    pageName = recommendedGamesTabs[page],
                    games = upcomingGames,
                    onGameClick = onGameClick
                )
                1 -> HomeGamesPage(
                    pageName = recommendedGamesTabs[page],
                    games = bestGames,
                    onGameClick = onGameClick
                )
                2 -> HomeGamesPage(
                    pageName = recommendedGamesTabs[page],
                    games = newGames,
                    onGameClick = onGameClick
                )
            }
        }
    }
}