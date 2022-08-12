package com.example.collections.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.collections.R
import com.fabledt5.common.components.RemoteImage
import com.fabledt5.common.theme.GradinentTextStyle
import com.fabledt5.common.theme.Mark
import com.fabledt5.common.theme.Proxima
import com.fabledt5.domain.model.items.GameItem
import kotlin.math.roundToInt

@ExperimentalMaterialApi
@Composable
fun FavoriteGame(
    gameItem: GameItem,
    itemHeight: Dp,
    onGameClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val contentWidth = LocalConfiguration.current.screenWidthDp.dp - 150.dp

    var isGameContentVisible by remember { mutableStateOf(false) }
    val favoriteGameContentVisibility by animateFloatAsState(
        targetValue = if (isGameContentVisible) 1f else 0f,
        animationSpec = tween(durationMillis = 500)
    )

    Box(modifier = modifier.height(itemHeight)) {
        if (isGameContentVisible)
            FavoriteGameContent(
                gameItem = gameItem,
                modifier = Modifier
                    .width(contentWidth)
                    .height(itemHeight)
                    .alpha(favoriteGameContentVisibility)
                    .padding(start = 15.dp, top = 5.dp, bottom = 5.dp)
                    .align(Alignment.CenterEnd)
            )
        FavoriteGameImage(
            gameImage = gameItem.gamePoster,
            gameTitle = gameItem.gameTitle,
            modifier = Modifier
                .fillMaxWidth()
                .height(itemHeight)
                .align(Alignment.Center),
            onImageLoadedSuccess = { isGameContentVisible = true },
            onGameClicked = onGameClicked
        )
    }
}

@Composable
fun FavoriteGameContent(
    gameItem: GameItem,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = gameItem.gameTitle.uppercase(),
            color = Color.White,
            fontFamily = Mark,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            OutlinedButton(
                onClick = { },
                shape = RoundedCornerShape(5.dp),
                border = BorderStroke(width = 1.dp, color = Color.White),
                colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Black)
            ) {
                Text(
                    text = stringResource(R.string.add_to_cart).uppercase(),
                    modifier = Modifier.padding(horizontal = 10.dp),
                    fontFamily = Mark,
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp,
                    style = GradinentTextStyle()
                )
            }
            Text(
                text = "79$",
                modifier = Modifier.padding(start = 20.dp),
                color = Color.White,
                fontFamily = Proxima,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.Bookmark,
                modifier = Modifier.size(20.dp),
                contentDescription = stringResource(R.string.ic_favorite),
                tint = Color.White
            )
            Text(
                text = stringResource(R.string.added_to_favorites).uppercase(),
                modifier = Modifier.padding(start = 10.dp),
                color = Color.White.copy(alpha = .7f),
                fontFamily = Mark,
                fontSize = 9.sp
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Share,
                modifier = Modifier.size(20.dp),
                contentDescription = stringResource(R.string.ic_favorite),
                tint = Color.White
            )
            Text(
                text = stringResource(R.string.share_with_friends).uppercase(),
                modifier = Modifier.padding(start = 10.dp),
                color = Color.White.copy(alpha = .7f),
                fontFamily = Mark,
                fontSize = 9.sp
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun FavoriteGameImage(
    gameImage: String?,
    gameTitle: String,
    onImageLoadedSuccess: () -> Unit,
    modifier: Modifier = Modifier,
    onGameClicked: () -> Unit
) {
    val swipeAbleState = rememberSwipeableState(initialValue = 0)

    val screenWidthDp = LocalConfiguration.current.screenWidthDp.dp - 150.dp
    val sizePx = with(LocalDensity.current) { screenWidthDp.toPx() }

    val anchors = mapOf(0f to 0, -sizePx to 1)

    RemoteImage(
        imagePath = gameImage,
        contentDescription = "$gameTitle game poster",
        modifier = modifier
            .swipeable(
                state = swipeAbleState, anchors = anchors, thresholds = { _, _ ->
                    FractionalThreshold(fraction = .3f)
                }, orientation = Orientation.Horizontal
            )
            .offset {
                IntOffset(
                    swipeAbleState.offset.value.roundToInt(), 0
                )
            }
            .clip(RoundedCornerShape(8.dp))
            .clickable { onGameClicked() }
            .shadow(elevation = 10.dp, shape = RoundedCornerShape(8.dp)),
        contentScale = ContentScale.Crop,
        onSuccess = onImageLoadedSuccess
    )
}