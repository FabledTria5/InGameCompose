package com.fabledt5.game.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import com.fabledt5.common.components.RemoteImage
import com.fabledt5.common.theme.Mark
import com.fabledt5.common.theme.Proxima
import com.fabledt5.common.theme.Turquoise
import com.fabledt5.common.utils.createFromHtml
import com.fabledt5.domain.model.Resource
import com.fabledt5.domain.model.items.GameItem
import com.fabledt5.domain.model.items.RatingItem
import com.fabledt5.game.R
import com.fabledt5.game.components.GameReviewItem
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@ExperimentalPagerApi
@Composable
fun AboutGamePage(
    gameItem: GameItem,
    gameSnapshots: Resource<List<String>>,
    ratingItem: Resource<RatingItem>,
    onShowReviewsClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(vertical = 10.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = gameItem.gameDescription.createFromHtml(),
            modifier = Modifier.padding(horizontal = 10.dp),
            color = Color.White.copy(alpha = .7f),
            fontFamily = Proxima,
        )
        when (gameSnapshots) {
            is Resource.Error -> Unit
            is Resource.Success -> ShowGameSnapshotsSuccess(gameSnapshots.data)
            else -> ShowGameSnapshotsLoading()
        }
        when (ratingItem) {
            is Resource.Error -> Unit
            is Resource.Success -> ShowGameReviewsSuccess(
                ratingItem = ratingItem.data,
                onShowAllClicked = onShowReviewsClicked
            )
            else -> ShowGameReviewsLoading()
        }
    }
}

@Composable
fun ShowGameSnapshotsLoading() {
    Box(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .fillMaxWidth()
            .height(200.dp),
    ) {
        Text(
            text = stringResource(R.string.snapshots).uppercase(),
            modifier = Modifier.padding(start = 10.dp),
            color = Color.White,
            fontFamily = Mark,
            fontWeight = FontWeight.Bold,
            fontSize = 21.sp
        )
        CircularProgressIndicator(
            modifier = Modifier
                .size(25.dp)
                .align(Alignment.Center),
            color = Turquoise
        )
    }
}

@ExperimentalPagerApi
@Composable
fun ShowGameSnapshotsSuccess(gameSnapshots: List<String>) {
    val snapshotsListState = rememberPagerState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        scope.launch {
            snapshotsListState.scrollToPage(gameSnapshots.size / 2)
        }
    }

    Column(
        modifier = Modifier
            .padding(vertical = 30.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.snapshots).uppercase(),
            modifier = Modifier.padding(start = 10.dp),
            color = Color.White,
            fontFamily = Mark,
            fontWeight = FontWeight.Bold,
            fontSize = 21.sp
        )
        HorizontalPager(
            count = gameSnapshots.size,
            modifier = Modifier
                .padding(top = 15.dp)
                .fillMaxWidth(),
            state = snapshotsListState,
            contentPadding = PaddingValues(horizontal = 50.dp)
        ) { page ->
            RemoteImage(
                imagePath = gameSnapshots[page],
                contentDescription = stringResource(R.string.game_snapshot),
                modifier = Modifier
                    .graphicsLayer {
                        val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                        lerp(
                            start = .9f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        ).also { scale ->
                            scaleX = scale
                            scaleY = scale
                        }
                    }
                    .fillMaxWidth()
                    .aspectRatio(ratio = .9f)
                    .clip(shape = RoundedCornerShape(size = 10.dp)),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun ShowGameReviewsLoading() {
    Box(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .fillMaxWidth()
            .height(200.dp),
    ) {
        Text(
            text = stringResource(id = R.string.rating),
            color = Color.White,
            fontFamily = Mark,
            fontWeight = FontWeight.Bold
        )
        CircularProgressIndicator(
            modifier = Modifier
                .size(25.dp)
                .align(Alignment.Center),
            color = Turquoise
        )
    }
}

@Composable
fun ShowGameReviewsSuccess(
    ratingItem: RatingItem,
    onShowAllClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = buildAnnotatedString {
                append(
                    AnnotatedString(
                        text = stringResource(R.string.rating).uppercase(),
                        spanStyle = SpanStyle(
                            color = Color.White,
                            fontFamily = Mark,
                            fontWeight = FontWeight.Bold
                        )
                    )
                )
                append(" ")
                append(
                    AnnotatedString(
                        text = ratingItem.gameRating,
                        spanStyle = SpanStyle(color = Color.White, fontWeight = FontWeight.Light)
                    )
                )
            },
            color = Color.White,
            fontFamily = Mark,
            fontWeight = FontWeight.Bold,
            fontSize = 21.sp
        )
        Row(
            modifier = Modifier.clickable { onShowAllClicked() },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.see_all),
                color = Color.Gray,
                fontFamily = Proxima,
                fontSize = 12.sp
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = stringResource(R.string.see_all),
                tint = Color.Gray
            )
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
    if (ratingItem.gameReviews.isNotEmpty() && ratingItem.gameReviews.size >= 2)
        ratingItem.gameReviews.subList(0, 2).forEach { reviewItem ->
            GameReviewItem(
                reviewItem = reviewItem,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
            )
        }
}