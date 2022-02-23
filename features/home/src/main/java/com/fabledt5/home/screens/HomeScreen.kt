package com.fabledt5.home.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.fabledt5.home.HomeViewModel
import com.fabledt5.home.items.HotGames
import com.fabledt5.home.items.PlatformsList
import com.fabledt5.home.items.RecommendedGamesPager
import com.fabledt5.home.items.RecommendedGamesTabs
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {
    val scope = rememberCoroutineScope()
    val gamesPagerState = rememberPagerState(initialPage = 0)

    val hotGamesList by homeViewModel.hotGamesList.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        HotGames(hotGames = hotGamesList, onGameClicked = {})
        RecommendedGamesTabs(
            gamesPagerState,
            onTabSelected = { index ->
                scope.launch { gamesPagerState.scrollToPage(index) }
            })
        PlatformsList(platformSelected = {})
        RecommendedGamesPager(gamesPagerState, onGameClick = {})
    }
}