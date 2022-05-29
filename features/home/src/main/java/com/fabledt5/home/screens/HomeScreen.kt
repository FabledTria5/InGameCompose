package com.fabledt5.home.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import com.fabledt5.common.components.OutlinedTabs
import com.fabledt5.home.HomeViewModel
import com.fabledt5.home.R
import com.fabledt5.home.components.PlatformsList
import com.fabledt5.home.items.HotGames
import com.fabledt5.home.pages.RecommendedGamesPager
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@ExperimentalFoundationApi
@ExperimentalPagerApi
@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {
    val scope = rememberCoroutineScope()
    val gamesPagerState = rememberPagerState(initialPage = 0)
    val scrollState = rememberScrollState()

    val hotGamesList by homeViewModel.hotGamesList.collectAsState()
    val platformsList by homeViewModel.platformsList.collectAsState()
    val favoritePlatform by homeViewModel.favoritePlatform.collectAsState()

    val upcomingGames by homeViewModel.upcomingGames.collectAsState()
    val bestGames by homeViewModel.bestGames.collectAsState()
    val newGames by homeViewModel.newGames.collectAsState()

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
            tabsTitles = stringArrayResource(id = R.array.home_screen_tabs),
            onTabSelected = { index ->
                scope.launch { gamesPagerState.scrollToPage(index) }
            }
        )
        PlatformsList(
            platformsList = platformsList,
            favoritePlatform = favoritePlatform,
            onPlatformSelected = { platformId ->
                homeViewModel.changeFavoritePlatformPlatform(platformId = platformId)
            }
        )
        RecommendedGamesPager(
            gamesPagerState = gamesPagerState,
            upcomingGames = upcomingGames,
            bestGames = bestGames,
            newGames = newGames,
            onGameClick = {
                homeViewModel.openGameScreen(gameId = it)
            }
        )
    }
}