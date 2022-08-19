package com.fabledt5.game.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fabledt5.common.components.RemoteImage
import com.fabledt5.common.theme.Mark
import com.fabledt5.common.utils.drawImageForeground
import com.fabledt5.domain.model.items.GameItem
import com.fabledt5.game.R

@Composable
fun GameHeader(
    gameItem: GameItem,
    onBackClicked: () -> Unit,
    onMarkAsPlayedClicked: (GameItem) -> Unit
) {
    val configuration = LocalConfiguration.current

    Box(modifier = Modifier.fillMaxWidth()) {
        RemoteImage(
            imagePath = gameItem.gamePoster,
            contentDescription = stringResource(
                id = R.string.game_poster_template,
                gameItem.gameTitle
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(height = (configuration.screenHeightDp.dp / 1.5.dp).dp)
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
                contentDescription = stringResource(R.string.icon_back),
                modifier = Modifier.size(30.dp),
                tint = Color.White
            )
        }
        IconButton(
            onClick = { onMarkAsPlayedClicked(gameItem) },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .statusBarsPadding()
        ) {
            Image(
                painter = painterResource(id = R.drawable.save),
                contentDescription = stringResource(R.string.mark_as_played),
                modifier = Modifier.size(35.dp),
                colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0f) })
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
                    text = gameItem.releaseDate.substringAfterLast(delimiter = " "),
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