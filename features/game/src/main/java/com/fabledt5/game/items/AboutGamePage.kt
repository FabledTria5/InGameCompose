package com.fabledt5.game.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import com.fabledt5.common.components.CoilImage
import com.fabledt5.common.theme.DarkLateGray
import com.fabledt5.common.theme.Mark
import com.fabledt5.common.theme.Proxima
import com.fabledt5.common.theme.SandyBrown
import com.fabledt5.game.R
import com.google.accompanist.pager.*
import kotlin.math.absoluteValue

@ExperimentalPagerApi
@Composable
fun AboutGamePage(onShowRatingsClicked: () -> Unit) {
    val snapshotsPagerState = rememberPagerState()

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "With the Empire attacking the Kingdoms of the North and the Wild Hunt, a cavalcade of ghastly riders, breathing down your neck, the only way to survive is to fight back. As Geralt of Rivia, a master swordsman and monster hunter, leave none of your enemies standing. Explore a gigantic open world, slay beasts and decide the fates of whole communities with your actions, all in a genuine next generation format. Also known as \"The Witcher III: Wild Hunt\"",
            modifier = Modifier.padding(horizontal = 10.dp),
            color = Color.White.copy(alpha = .7f),
            fontFamily = Proxima,
        )
        GameSnapshots(snapshotsPagerState = snapshotsPagerState, gameSnapshots = listOf())
        GameRatings(gameRating = 4.9f, onShowAllClicked = onShowRatingsClicked)
        GameFranchise()
    }
}

@ExperimentalPagerApi
@Composable
fun GameSnapshots(snapshotsPagerState: PagerState, gameSnapshots: List<String>) {
    Column(
        modifier = Modifier
            .padding(vertical = 30.dp, horizontal = 10.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.snapshots).uppercase(),
            color = Color.White,
            fontFamily = Mark,
            fontWeight = FontWeight.Bold,
            fontSize = 21.sp
        )
        HorizontalPager(
            count = gameSnapshots.size,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            state = snapshotsPagerState
        ) { page ->
            CoilImage(
                imagePath = gameSnapshots[page],
                contentDescription = "Game Snapshot",
                modifier = Modifier
                    .fillMaxWidth(fraction = .8f)
                    .height(200.dp)
                    .graphicsLayer {
                        val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                        lerp(
                            start = .85f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        ).also { scale ->
                            scaleX = scale
                            scaleY = scale
                        }
                    },
                scaleType = ContentScale.Crop
            )
        }
    }
}

@Composable
fun GameRatings(gameRating: Float, onShowAllClicked: () -> Unit) {
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
                        text = gameRating.toString(),
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
    Spacer(modifier = Modifier.height(15.dp))
    repeat(times = 2) { index ->
        RatingItem()
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
fun RatingItem() {
    Card(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .fillMaxWidth(), backgroundColor = DarkLateGray
    ) {
        Column(
            modifier = Modifier
                .padding(all = 10.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Nick Mollow",
                        color = Color.White,
                        fontFamily = Mark,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(
                        text = "14 days ago",
                        color = Color.Gray,
                        fontFamily = Proxima,
                        fontSize = 10.sp
                    )
                }
                Row {
                    repeat(times = 5) {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = stringResource(R.string.icon_star),
                            modifier = Modifier
                                .padding(top = 5.dp)
                                .size(10.dp),
                            tint = SandyBrown
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "It’s a story worthy of a place in the more accepted subculture of dark fantasy ruled across media by Game of Thrones. First-timers will easily love this facet but may also be surprised to learn that this series, and the books it’s based upon, have been the at the fore of adult and mature storytelling for a long time. Wild Hunt is both at times brutal and sexy, with a juxtaposition of hard-edged steel (or silver), blood and death being met with soft, naked skin; passion, lust and even love.",
                color = Color.White.copy(alpha = .7f),
                fontFamily = Proxima,
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun GameFranchise() {
    Column(
        modifier = Modifier
            .padding(top = 30.dp, bottom = 10.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.same_series).uppercase(),
            modifier = Modifier.padding(start = 10.dp),
            color = Color.White,
            fontFamily = Mark,
            fontWeight = FontWeight.Bold,
            fontSize = 21.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            contentPadding = PaddingValues(horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            items(count = 5) {
                Image(
                    painter = painterResource(id = R.drawable.god_of_war_placeholder),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(size = 10.dp))
                )
                if (it < 4)
                    Spacer(modifier = Modifier.width(5.dp))
            }
        }
    }
}

@ExperimentalPagerApi
@Preview(showBackground = true, backgroundColor = 0xFF18181C)
@Composable
fun AboutGamePagePreview() {
    AboutGamePage() {

    }
}