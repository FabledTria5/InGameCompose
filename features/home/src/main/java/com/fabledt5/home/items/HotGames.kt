package com.fabledt5.home.items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fabledt5.common.components.ColorfulProgressIndicator
import com.fabledt5.common.components.GradientPagerIndicators
import com.fabledt5.common.components.RemoteImage
import com.fabledt5.common.theme.Mark
import com.fabledt5.common.theme.Proxima
import com.fabledt5.common.utils.autoScroll
import com.fabledt5.common.utils.drawImageForeground
import com.fabledt5.domain.model.GameItem
import com.fabledt5.domain.model.Resource
import com.fabledt5.home.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@ExperimentalPagerApi
@Composable
fun HotGames(hotGames: Resource<List<GameItem>>, onGameClicked: (Int) -> Unit) {
    when (hotGames) {
        is Resource.Success -> ShowHotGames(hotGames.data, onGameClicked = onGameClicked)
        is Resource.Error -> ShowHotGamesError()
        else -> ShowHotGamesLoading()
    }
}

@Composable
fun ShowHotGamesLoading() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp),
        contentAlignment = Alignment.Center
    ) {
        ColorfulProgressIndicator(modifier = Modifier.size(35.dp))
    }
}

@Composable
fun ShowHotGamesError() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = stringResource(R.string.hot_games_error))
    }
}

@ExperimentalPagerApi
@Composable
fun ShowHotGames(hotGames: List<GameItem>, onGameClicked: (Int) -> Unit) {
    val pagerState = rememberPagerState()
    var screenSize by remember { mutableStateOf(IntSize.Zero) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .onGloballyPositioned { screenSize = it.size },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            count = hotGames.size,
            modifier = Modifier
                .fillMaxWidth()
                .autoScroll(pagerState),
            state = pagerState
        ) {
            HotGame(hotGame = hotGames[it], onGameClicked = onGameClicked)
        }

        GradientPagerIndicators(
            pagerState = pagerState,
            modifier = Modifier.padding(10.dp),
            inactiveColor = Color.DarkGray.copy(alpha = .3f),
            indicatorWidth = with(LocalDensity.current) {
                screenSize.width.toDp() / (hotGames.size + 2)
            },
            indicatorHeight = 3.dp,
            indicatorShape = RoundedCornerShape(5.dp),
            spacing = 10.dp
        )
    }
}

@Composable
fun HotGame(hotGame: GameItem, onGameClicked: (Int) -> Unit) {
    Box(modifier = Modifier.clickable { onGameClicked(hotGame.gameId) }) {
        RemoteImage(
            imagePath = hotGame.gamePoster,
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
                fontSize = 27.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )
            Spacer(modifier = Modifier.height(5.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = hotGame.gamePEGIRating,
                    color = Color.White.copy(alpha = .5f),
                    fontFamily = Proxima,
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = hotGame.gameReleaseYear,
                    color = Color.White.copy(alpha = .5f),
                    fontFamily = Proxima,
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = hotGame.gameGenres,
                    color = Color.White.copy(alpha = .5f),
                    fontFamily = Proxima,
                    fontSize = 12.sp
                )
            }
        }
    }
}