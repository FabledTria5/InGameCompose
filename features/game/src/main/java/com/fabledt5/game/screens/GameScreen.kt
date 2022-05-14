package com.fabledt5.game.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.fabledt5.common.components.ColorfulProgressIndicator
import com.fabledt5.common.components.OutlinedTabs
import com.fabledt5.common.theme.Mark
import com.fabledt5.domain.model.GameItem
import com.fabledt5.domain.model.GameRating
import com.fabledt5.domain.model.Resource
import com.fabledt5.game.GameViewModel
import com.fabledt5.game.R
import com.fabledt5.game.items.GameHeader
import com.fabledt5.game.pages.AboutGamePage
import com.fabledt5.game.pages.InfoGamePage
import com.fabledt5.game.pages.RequirementsGamePage
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

    BackHandler { gameViewModel.onBackClicked() }

    ShowGameScreen(
        gameData = gameData,
        gameSnapshots = gameSnapshots,
        gameRating = gameReviews,
        onShowReviewsClicked = { gameViewModel.openReviewsScreen() },
        onBackClicked = { gameViewModel.onBackClicked() }
    )
}

@ExperimentalPagerApi
@Composable
fun ShowGameScreen(
    gameData: Resource<GameItem>,
    gameSnapshots: Resource<List<String>>,
    gameRating: Resource<GameRating>,
    onShowReviewsClicked: () -> Unit,
    onBackClicked: () -> Unit
) {
    when (gameData) {
        is Resource.Error -> ShowGameLoadingError()
        is Resource.Success -> ShowGameLoadingSuccess(
            gameItem = gameData.data,
            gameSnapshots = gameSnapshots,
            gameRating = gameRating,
            onShowReviewsClicked = onShowReviewsClicked,
            onBackClicked = onBackClicked
        )
        else -> ShowGameLoading()
    }
}

@Composable
fun ShowGameLoading() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        ColorfulProgressIndicator(modifier = Modifier.size(35.dp))
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
    gameRating: Resource<GameRating>,
    onShowReviewsClicked: () -> Unit,
    onBackClicked: () -> Unit
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
        GameHeader(gameItem = gameItem, onBackClicked = onBackClicked)
        OutlinedTabs(
            pagerState = gameDataPagerState,
            tabsTitles = gameDataTabs,
            textSize = 10.sp,
            onTabSelected = { tabIndex ->
                scope.launch { gameDataPagerState.animateScrollToPage(tabIndex) }
            })
        HorizontalPager(
            count = gameDataTabs.size,
            modifier = Modifier.wrapContentHeight(),
            state = gameDataPagerState,
            verticalAlignment = Alignment.Top
        ) { page ->
            when (page) {
                0 -> AboutGamePage(
                    gameItem = gameItem,
                    gameSnapshots = gameSnapshots,
                    gameRating = gameRating,
                    onShowReviewsClicked = onShowReviewsClicked
                )
                1 -> InfoGamePage(gameItem = gameItem)
                2 -> RequirementsGamePage(gameRequirements = gameItem.gameRequirements)
            }
        }
    }
}
