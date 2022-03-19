package com.fabledt5.game.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.unit.sp
import com.fabledt5.common.components.OutlinedTabs
import com.fabledt5.game.GameViewModel
import com.fabledt5.game.R
import com.fabledt5.game.items.AboutGamePage
import com.fabledt5.game.items.GameHeader
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@Composable
fun GameScreen(gameViewModel: GameViewModel) {
    val scope = rememberCoroutineScope()
    val screenScrollState = rememberScrollState()
    val gameDataPagerState = rememberPagerState()
    val gameDataTabs = stringArrayResource(id = R.array.game_data_tabs)

    val gameData by gameViewModel.gameData.collectAsState()
    val gameSnapshots by gameViewModel.gameSnapshots.collectAsState()
    val gameReviews by gameViewModel.gameReviews.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(screenScrollState)
    ) {
        GameHeader(gameData = gameData, onBackClicked = { })
        OutlinedTabs(
            pagerState = gameDataPagerState,
            tabsTitles = gameDataTabs,
            textSize = 10.sp,
            onTabSelected = { tabIndex ->
                scope.launch { gameDataPagerState.animateScrollToPage(tabIndex) }
            })
        HorizontalPager(
            count = gameDataTabs.size,
            state = gameDataPagerState,
            userScrollEnabled = false
        ) { page ->
            when (page) {
                0 -> AboutGamePage(
                    gameData = gameData,
                    gameSnapshots = gameSnapshots,
                    gameReviews = gameReviews,
                    onShowReviewsClicked = { gameViewModel.openReviewsScreen() }
                )
            }
        }
    }
}