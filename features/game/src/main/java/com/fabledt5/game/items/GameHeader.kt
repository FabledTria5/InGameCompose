package com.fabledt5.game.items

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fabledt5.common.components.CoilImage
import com.fabledt5.common.components.VideoPlayer
import com.fabledt5.common.theme.Mark
import com.fabledt5.common.utils.drawImageForeground
import com.fabledt5.domain.model.GameItem
import com.fabledt5.domain.model.Resource
import com.google.accompanist.insets.statusBarsPadding

@Composable
fun GameHeader(onBackClicked: () -> Unit, gameData: Resource<GameItem>) {
    when (gameData) {
        is Resource.Error -> ShowEmptyHeader()
        is Resource.Success -> ShowHeaderContent(
            onBackClicked = onBackClicked,
            gameItem = gameData.data
        )
        else -> ShowLoadingHeader()
    }
}

@Composable
fun ShowLoadingHeader() {
    val configuration = LocalConfiguration.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = configuration.screenHeightDp.dp / 2)
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(20.dp)
                .align(Alignment.Center)
        )
    }
}

@Composable
fun ShowEmptyHeader() {
    val configuration = LocalConfiguration.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = configuration.screenHeightDp.dp / 2)
    ) {
        Text(
            text = "Game data can not be loaded",
            modifier = Modifier.fillMaxWidth(fraction = .8f),
            textAlign = TextAlign.Center,
            fontFamily = Mark,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
    }
}

@Composable
fun ShowHeaderContent(onBackClicked: () -> Unit, gameItem: GameItem) {
    val configuration = LocalConfiguration.current

    Box(modifier = Modifier.fillMaxWidth()) {
        CoilImage(
            imagePath = gameItem.gamePoster,
            contentDescription = "${gameItem.gameTitle} game poster",
            modifier = Modifier
                .fillMaxWidth()
                .height(height = (configuration.screenHeightDp.dp / 1.5.dp).dp)
                .drawWithContent {
                    drawContent()
                    drawImageForeground()
                },
            scaleType = ContentScale.Crop
        )
        gameItem.gameTrailerUrl?.let { url ->
            VideoPlayer(
                url = url,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height = (configuration.screenHeightDp.dp / 1.5.dp).dp)
                    .drawWithContent {
                        drawContent()
                        drawImageForeground()
                    }
            )
        }
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
                text = gameItem.gameTitle,
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
                text = gameItem.gameDeveloper.uppercase(),
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
                    text = gameItem.gamePEGIRating,
                    modifier = Modifier.padding(end = 10.dp),
                    color = Color.White.copy(alpha = .4f),
                    fontSize = 11.sp,
                    fontFamily = Mark
                )
                Text(
                    text = gameItem.gameReleaseYear,
                    modifier = Modifier.padding(end = 10.dp),
                    color = Color.White.copy(alpha = .4f),
                    fontSize = 11.sp,
                    fontFamily = Mark
                )
                Text(
                    text = gameItem.gameGenres,
                    modifier = Modifier.padding(end = 10.dp),
                    color = Color.White.copy(alpha = .4f),
                    fontSize = 11.sp,
                    fontFamily = Mark
                )
            }
        }
    }
}