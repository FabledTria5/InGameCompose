package com.fabledt5.game.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fabledt5.common.theme.Mark
import com.fabledt5.common.theme.MidNightBlack
import com.fabledt5.common.theme.Proxima
import com.fabledt5.domain.model.GameItem
import com.fabledt5.domain.model.Resource
import com.fabledt5.domain.model.ReviewItem
import com.fabledt5.game.GameViewModel
import com.fabledt5.game.R
import com.fabledt5.game.composables.GameReviewItem
import com.fabledt5.game.items.RatingCounter
import com.fabledt5.game.utils.toRatingsCounter

@Composable
fun ReviewsScreen(gameViewModel: GameViewModel) {
    val gameItem by gameViewModel.gameData.collectAsState()
    val gameReviews by gameViewModel.gameReviews.collectAsState()

    ShowReviewsScreen(gameItem, gameReviews)
}

@Composable
fun ShowReviewsScreen(gameItem: Resource<GameItem>, gameReviews: Resource<List<ReviewItem>>) {
    if (gameItem is Resource.Success && gameReviews is Resource.Success) {
        ShowGameReviewsSuccess(gameItem = gameItem.data, gameReviews = gameReviews.data)
    }
}

@Composable
fun ShowGameReviewsSuccess(gameItem: GameItem, gameReviews: List<ReviewItem>) {
    Scaffold(
        modifier = Modifier.systemBarsPadding(),
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MidNightBlack),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.reviews).uppercase(),
                    modifier = Modifier.padding(vertical = 15.dp),
                    color = Color.White,
                    fontFamily = Mark,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(start = 10.dp, top = 5.dp, end = 10.dp, bottom = 10.dp),
        ) {
            item {
                Column(
                    modifier = Modifier
                        .padding(bottom = 15.dp)
                        .fillMaxSize()
                ) {
                    Text(
                        text = gameItem.gameTitle,
                        modifier = Modifier
                            .align(CenterHorizontally)
                            .padding(top = 5.dp),
                        color = Color.White,
                        fontFamily = Mark,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = buildAnnotatedString {
                            append(
                                AnnotatedString(
                                    text = stringResource(R.string.estimate),
                                    spanStyle = SpanStyle(
                                        color = Color.White,
                                        fontSize = 13.sp,
                                        fontFamily = Proxima
                                    )
                                )
                            )
                            append(
                                AnnotatedString(
                                    text = " ${gameItem.gameRating}",
                                    spanStyle = SpanStyle(
                                        color = Color.White,
                                        fontSize = 13.sp,
                                        fontFamily = Mark,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                            )
                        },
                        modifier = Modifier.padding(bottom = 10.dp)
                    )
                    GameRatingsCounters(
                        gameRatings = gameReviews.toRatingsCounter(),
                        totalReviews = gameReviews.size
                    )
                }
            }
            items(gameReviews, key = { it.reviewerName }) {
                GameReviewItem(
                    reviewItem = it,
                    modifier = Modifier.padding(vertical = 5.dp)
                )
            }
        }
    }
}

@Composable
fun GameRatingsCounters(gameRatings: Map<Int, Int>, totalReviews: Int) {
    gameRatings.keys.forEach { key ->
        val percent = gameRatings[key]?.toFloat()?.div(totalReviews)?.times(100)?.toInt() ?: 0
        RatingCounter(
            rating = key,
            ratingsCount = gameRatings[key],
            ratingsPercent = percent
        )
    }
}