package com.fabledt5.common.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fabledt5.common.theme.Mark
import com.fabledt5.common.theme.Proxima
import com.fabledt5.domain.model.items.GameItem

@Composable
fun SharedGameItem(
    gameItem: GameItem,
    onGameClicked: (Int) -> Unit,
    modifier: Modifier = Modifier,
    gameAction: @Composable () -> Unit = {},
) {
    var isImageLoaded by remember { mutableStateOf(false) }

    Row(modifier = modifier.clickable { onGameClicked(gameItem.gameId) }) {
        RemoteImage(
            imagePath = gameItem.gamePoster,
            contentDescription = "${gameItem.gameTitle} game poster",
            modifier = Modifier
                .weight(weight = 1f)
                .height(100.dp)
                .clip(RoundedCornerShape(7.dp))
                .then(if (isImageLoaded) Modifier.shadow(elevation = 10.dp) else Modifier),
            contentScale = ContentScale.Crop,
            onSuccess = { isImageLoaded = true }
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column(
            modifier = Modifier
                .weight(weight = 1f)
                .height(100.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = gameItem.gameTitle,
                    modifier = Modifier.padding(bottom = 5.dp),
                    color = Color.White,
                    fontFamily = Mark,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = gameItem.gameGenres,
                    modifier = Modifier.padding(bottom = 5.dp),
                    color = Color.White.copy(alpha = .3f),
                    fontFamily = Proxima,
                    fontSize = 11.sp
                )
                Text(
                    text = "Release date: ${gameItem.releaseDate}",
                    modifier = Modifier.padding(bottom = 5.dp),
                    color = Color.White.copy(alpha = .3f),
                    fontFamily = Proxima,
                    fontSize = 11.sp
                )
                gameAction()
            }
        }
    }
}