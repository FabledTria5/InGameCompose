package com.example.collections.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.collections.R
import com.fabledt5.common.components.RemoteImage
import com.fabledt5.common.theme.Mark
import com.fabledt5.common.theme.MidNightBlack
import com.fabledt5.domain.model.items.GameItem

@Composable
fun CalendarGame(
    gameItem: GameItem,
    onGameClicked: (Int) -> Unit,
    onAddToPlayedClicked: (GameItem) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(bottom = 15.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(8.dp))
            .background(MidNightBlack)
            .clickable { onGameClicked(gameItem.gameId) }
            .padding(horizontal = 10.dp, vertical = 15.dp)
    ) {
        Row {
            RemoteImage(
                imagePath = gameItem.gamePoster,
                contentDescription = "${gameItem.gameTitle} game poster",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .shadow(elevation = 5.dp, shape = RoundedCornerShape(5.dp))
                    .size(width = 100.dp, height = 90.dp)
                    .clip(RoundedCornerShape(5.dp))
            )
            Spacer(modifier = Modifier.width(15.dp))
            Column(
                modifier = Modifier.height(90.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = gameItem.gameTitle,
                        color = Color.White,
                        fontFamily = Mark,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = "Last update: ${gameItem.gameLastUpdate}",
                        modifier = Modifier.padding(top = 5.dp),
                        color = Color.White.copy(alpha = .3f),
                        fontFamily = Mark,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Text(
                    text = stringResource(R.string.add_to_played),
                    modifier = Modifier.clickable { onAddToPlayedClicked(gameItem) },
                    color = Color.White.copy(alpha = .3f),
                    fontFamily = Mark,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}