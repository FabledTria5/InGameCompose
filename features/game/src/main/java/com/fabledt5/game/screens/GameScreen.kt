package com.fabledt5.game.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fabledt5.common.components.OutlinedTabs
import com.fabledt5.common.theme.Mark
import com.fabledt5.common.theme.Turquoise
import com.fabledt5.domain.model.GameItem
import com.fabledt5.domain.model.Resource
import com.fabledt5.domain.model.ReviewItem
import com.fabledt5.game.GameViewModel
import com.fabledt5.game.R
import com.fabledt5.game.items.AboutGamePage
import com.fabledt5.game.items.GameHeader
import com.fabledt5.game.items.InfoGamePage
import com.fabledt5.game.items.RequirementsGamePage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@Composable
fun GameScreen(gameViewModel: GameViewModel) {
    val gameData by gameViewModel.gameData.collectAsState()
    val gameSnapshots by gameViewModel.gameSnapshots.collectAsState()
    val gameReviews by gameViewModel.gameReviews.collectAsState()

    ShowGameScreen(
        gameData = gameData,
        gameSnapshots = gameSnapshots,
        gameReviews = gameReviews,
        onShowReviewsClicked = { gameViewModel.openReviewsScreen() }
    )
}

@ExperimentalPagerApi
@Composable
fun ShowGameScreen(
    gameData: Resource<GameItem>,
    gameSnapshots: Resource<List<String>>,
    gameReviews: Resource<List<ReviewItem>>,
    onShowReviewsClicked: () -> Unit
) {
    when (gameData) {
        is Resource.Error -> ShowGameLoadingError()
        is Resource.Success -> ShowGameLoadingSuccess(
            gameItem = gameData.data,
            gameSnapshots = gameSnapshots,
            gameReviews = gameReviews,
            onShowReviewsClicked = onShowReviewsClicked
        )
        else -> ShowGameLoading()
    }
}

@Composable
fun ShowGameLoading() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(modifier = Modifier.size(25.dp), color = Turquoise)
    }
}

@Composable
fun ShowGameLoadingError() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_sad_face),
            contentDescription = stringResource(R.string.icon_loading_error),
            tint = Color.LightGray
        )
        Text(
            text = stringResource(R.string.game_loading_error_message),
            modifier = Modifier
                .padding(top = 15.dp)
                .fillMaxWidth(fraction = .8f),
            color = Color.White,
            fontFamily = Mark,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
    }
}

@ExperimentalPagerApi
@Composable
fun ShowGameLoadingSuccess(
    gameItem: GameItem,
    gameSnapshots: Resource<List<String>>,
    gameReviews: Resource<List<ReviewItem>>,
    onShowReviewsClicked: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val screenScrollState = rememberScrollState()
    val gameDataPagerState = rememberPagerState()
    val gameDataTabs = stringArrayResource(id = R.array.game_data_tabs)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .verticalScroll(screenScrollState)
    ) {
        GameHeader(gameItem = gameItem, onBackClicked = { })
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
                    gameItem = gameItem,
                    gameSnapshots = gameSnapshots,
                    gameReviews = gameReviews,
                    onShowReviewsClicked = onShowReviewsClicked
                )
                1 -> InfoGamePage(gameItem = gameItem)
                2 -> RequirementsGamePage(gameRequirements = gameItem.gameRequirements)
            }
        }
    }
}
