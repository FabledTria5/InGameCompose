package com.fabledt5.home.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fabledt5.common.theme.Mark
import com.fabledt5.common.theme.Proxima
import com.fabledt5.common.theme.Turquoise
import com.fabledt5.common.utils.drawImageForeground
import com.fabledt5.domain.model.GameItem
import com.fabledt5.home.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import kotlin.concurrent.fixedRateTimer

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun HomeScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        HotGames(
            listOf(
                GameItem(
                    gameId = 0,
                    gameTitle = "God of war",
                    gamePEGIRating = "18+",
                    gameReleaseYear = "2018",
                    gameGenres = "action, adventure"
                ),
                GameItem(gameId = 0),
                GameItem(gameId = 0),
                GameItem(gameId = 0),
                GameItem(gameId = 0),
                GameItem(gameId = 0),
            )
        )
        RecommendedGames()
    }
}

@ExperimentalPagerApi
@Composable
fun HotGames(hotGames: List<GameItem>) {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    var screenSize by remember { mutableStateOf(IntSize.Zero) }

    LaunchedEffect(key1 = true) {
        fixedRateTimer(initialDelay = 5000L, period = 5000L) {
            scope.launch {
                pagerState.scrollToPage(
                    if (pagerState.currentPage == hotGames.size) 0
                    else pagerState.currentPage + 1
                )
            }
        }
    }

    HorizontalPager(
        count = hotGames.size,
        modifier = Modifier.fillMaxWidth(),
        state = pagerState
    ) {
        HotGame(hotGame = hotGames[it])
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 10.dp)
            .onGloballyPositioned { screenSize = it.size },
        contentAlignment = Alignment.Center
    ) {
        HorizontalPagerIndicator(
            pagerState = pagerState,
            activeColor = Turquoise,
            inactiveColor = Color.DarkGray.copy(alpha = .3f),
            indicatorWidth = with(LocalDensity.current) { screenSize.width.toDp() / (hotGames.size + 2) },
            indicatorHeight = 3.dp,
            indicatorShape = RoundedCornerShape(5.dp),
            spacing = 10.dp
        )
    }
}

@Composable
fun HotGame(hotGame: GameItem) {
    Box {
        Image(
            painter = painterResource(id = R.drawable.god_of_war_placeholder),
            contentDescription = "${hotGame.gameTitle} game poster",
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .drawWithContent {
                    drawContent()
                    drawImageForeground()
                },
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(all = 10.dp)
        ) {
            Text(
                text = hotGame.gameTitle,
                color = Color.White,
                fontFamily = Mark,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )
            Spacer(modifier = Modifier.height(5.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = hotGame.gamePEGIRating,
                    color = Color.White.copy(alpha = .5f),
                    fontFamily = Proxima
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = hotGame.gameReleaseYear,
                    color = Color.White.copy(alpha = .5f),
                    fontFamily = Proxima
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = hotGame.gameGenres,
                    color = Color.White.copy(alpha = .5f),
                    fontFamily = Proxima
                )
            }
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun RecommendedGames() {
    val gamesPagerState = rememberPagerState(initialPage = 0)
    val scope = rememberCoroutineScope()
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
                    onClick = { scope.launch { gamesPagerState.scrollToPage(index) } },
                    modifier = Modifier
                        .background(
                            color = if (gamesPagerState.currentPage == index) Color(0xFF0c0d0e)
                            else Color.Transparent,
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
    PlatformsList()
}

@ExperimentalMaterialApi
@Composable
fun PlatformsList() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.for_platform).uppercase(),
            color = Color.White.copy(alpha = .9f),
            fontWeight = FontWeight.Bold,
            fontFamily = Mark,
            fontSize = 17.sp
        )
    }
}

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Preview(showBackground = true, backgroundColor = 0xFF18181C)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}