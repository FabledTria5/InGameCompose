package com.fabledt5.game.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fabledt5.common.components.OutlinedTabs
import com.fabledt5.common.theme.Mark
import com.fabledt5.common.utils.drawImageForeground
import com.fabledt5.game.R
import com.fabledt5.game.items.AboutGamePage
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@Composable
fun GameScreen() {
    val scope = rememberCoroutineScope()
    val screenScrollState = rememberScrollState()
    val gameDataPagerState = rememberPagerState()
    val gameDataTabs = stringArrayResource(id = R.array.game_data_tabs)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(screenScrollState)
    ) {
        GameHeader(onBackClicked = { })
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
                0 -> AboutGamePage(onShowRatingsClicked = {})
            }
        }
    }
}

@Composable
fun GameHeader(onBackClicked: () -> Unit) {
    val configuration = LocalConfiguration.current

    Box(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(R.drawable.god_of_war_placeholder),
            contentDescription = "Game Poster",
            modifier = Modifier
                .fillMaxWidth()
                .height(height = configuration.screenHeightDp.dp / 2)
                .drawWithContent {
                    drawContent()
                    drawImageForeground()
                },
            contentScale = ContentScale.Crop
        )
        IconButton(
            onClick = onBackClicked,
            modifier = Modifier
                .align(Alignment.TopStart)
                .statusBarsPadding()
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "Back button",
                modifier = Modifier.size(30.dp),
                tint = Color.White
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
                .align(Alignment.BottomCenter)
        ) {
            Text(
                text = "The Witcher 3: Wild hunt",
                modifier = Modifier.fillMaxWidth(fraction = .7f),
                fontFamily = Mark,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                fontSize = 32.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "CD PROJECT RED",
                color = Color.White.copy(alpha = .6f),
                fontFamily = Mark,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp)
            ) {
                Text(
                    text = "18+",
                    modifier = Modifier.padding(end = 10.dp),
                    color = Color.White.copy(alpha = .4f),
                    fontSize = 11.sp,
                    fontFamily = Mark
                )
                Text(
                    text = "2015",
                    modifier = Modifier.padding(end = 10.dp),
                    color = Color.White.copy(alpha = .4f),
                    fontSize = 11.sp,
                    fontFamily = Mark
                )
                Text(
                    text = "open world, rpg, atmospheric",
                    modifier = Modifier.padding(end = 10.dp),
                    color = Color.White.copy(alpha = .4f),
                    fontSize = 11.sp,
                    fontFamily = Mark
                )
            }
        }
    }
}

@ExperimentalPagerApi
@Preview(showBackground = true, backgroundColor = 0xFF18181C)
@Composable
fun GameScreenPreview() {
    GameScreen()
}