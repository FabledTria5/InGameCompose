package com.fabledt5.common.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fabledt5.common.R
import com.fabledt5.common.theme.DefaultHorizontalGradient
import com.fabledt5.common.theme.Mark
import com.fabledt5.common.theme.Proxima
import com.fabledt5.common.theme.SandyBrown
import com.fabledt5.common.utils.gradient
import kotlin.math.roundToInt

@ExperimentalMaterialApi
@Composable
fun FavoriteGame(
    onOpenWebSiteClicked: (url: String) -> Unit,
    onAddedToFavouritesClicked: (gameId: Int) -> Unit,
    onShareClicked: () -> Unit
) {
    SwipeableComposable(
        LowerLayer = {
            Column(
                modifier = Modifier
                    .height(250.dp)
                    .fillMaxWidth(fraction = .6f)
                    .align(Alignment.CenterEnd),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = "The Elder Scrolls V: Skyrim",
                    color = Color.White,
                    fontFamily = Mark,
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedButton(
                        onClick = { onOpenWebSiteClicked("Some url") },
                        shape = RoundedCornerShape(7.dp),
                        border = BorderStroke(width = 1.dp, color = Color.White),
                        colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Black)
                    ) {
                        Text(
                            text = "Open website".uppercase(),
                            modifier = Modifier
                                .padding(horizontal = 10.dp, vertical = 5.dp)
                                .gradient(DefaultHorizontalGradient),
                            fontFamily = Mark,
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.sp
                        )
                    }
                    Row(
                        modifier = Modifier.padding(start = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "93",
                            modifier = Modifier.padding(end = 3.dp),
                            color = Color.White,
                            fontFamily = Mark,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.ic_star_filled),
                            contentDescription = stringResource(id = R.string.icon_star),
                            modifier = Modifier.size(18.dp),
                            tint = SandyBrown
                        )
                    }
                }
                Row(
                    modifier = Modifier.clickable { onAddedToFavouritesClicked(-1) },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_mark),
                        contentDescription = stringResource(R.string.icon_favorite),
                        modifier = Modifier
                            .padding(end = 15.dp)
                            .size(18.dp),
                        tint = Color.White
                    )
                    Text(
                        text = stringResource(R.string.added_to_favourites).uppercase(),
                        color = Color.White.copy(alpha = .4f),
                        fontFamily = Proxima,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                }
                Row(
                    modifier = Modifier.clickable { onShareClicked() },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = stringResource(R.string.icon_favorite),
                        modifier = Modifier
                            .padding(end = 15.dp)
                            .size(18.dp),
                        tint = Color.White
                    )
                    Text(
                        text = stringResource(R.string.share_with_friends).uppercase(),
                        color = Color.White.copy(alpha = .4f),
                        fontFamily = Proxima,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                }
            }
        },
        MainLayer = { modifier ->
            Image(
                painter = painterResource(id = R.drawable.god_of_war_placeholder),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .clip(RoundedCornerShape(10.dp))
                    .fillMaxWidth()
                    .height(250.dp)
                    .shadow(elevation = 10.dp, shape = RoundedCornerShape(10.dp))
            )
        },
        modifier = Modifier.padding(horizontal = 10.dp),
        offsetSize = 240.dp
    )
}

@ExperimentalMaterialApi
@Composable
fun SwipeableComposable(
    modifier: Modifier = Modifier,
    LowerLayer: @Composable BoxScope.() -> Unit,
    MainLayer: @Composable BoxScope.(modifier: Modifier) -> Unit,
    offsetSize: Dp
) {
    val swipeAbleState = rememberSwipeableState(initialValue = 0)
    val sizePx = with(LocalDensity.current) { offsetSize.toPx() }
    val anchors = mapOf(0f to 0, -sizePx to 1)

    Box(
        modifier = modifier.swipeable(
            state = swipeAbleState,
            anchors = anchors,
            thresholds = { _, _ ->
                FractionalThreshold(fraction = .3f)
            },
            orientation = Orientation.Horizontal
        )
    ) {
        LowerLayer()
        MainLayer(modifier = Modifier.offset {
            IntOffset(
                swipeAbleState.offset.value.roundToInt(), 0
            )
        })
    }
}

@ExperimentalMaterialApi
@Preview(showBackground = true, backgroundColor = 0xFF121214)
@Composable
fun CardPrev() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        FavoriteGame(onOpenWebSiteClicked = {}, onAddedToFavouritesClicked = {}) {

        }
    }
}
