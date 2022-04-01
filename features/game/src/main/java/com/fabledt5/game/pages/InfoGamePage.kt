package com.fabledt5.game.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fabledt5.common.theme.Proxima
import com.fabledt5.domain.model.GameItem
import com.fabledt5.game.R

@Composable
fun InfoGamePage(gameItem: GameItem) {
    Column(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(bottom = 10.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.release_date),
                modifier = Modifier
                    .weight(weight = .3f),
                color = Color.White.copy(alpha = .4f),
                fontFamily = Proxima,
                fontSize = 14.sp
            )
            Text(
                text = gameItem.gameReleaseYear,
                modifier = Modifier.weight(weight = .7f),
                fontFamily = Proxima,
                color = Color.White,
                fontSize = 14.sp
            )
        }
        Row(
            modifier = Modifier
                .padding(bottom = 10.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.directors),
                modifier = Modifier.weight(weight = .3f),
                color = Color.White.copy(alpha = .4f),
                fontFamily = Proxima,
                fontSize = 14.sp
            )
            Text(
                text = gameItem.gameDirectors.ifEmpty { stringResource(R.string.unknown) },
                modifier = Modifier.weight(weight = .7f),
                fontFamily = Proxima,
                color = Color.White,
                fontSize = 14.sp
            )
        }
        Row(
            modifier = Modifier
                .padding(bottom = 10.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.writers),
                modifier = Modifier.weight(weight = .3f),
                color = Color.White.copy(alpha = .4f),
                fontFamily = Proxima,
                fontSize = 14.sp
            )
            Text(
                text = gameItem.gameWriters.ifEmpty { stringResource(R.string.unknown) },
                modifier = Modifier.weight(weight = .7f),
                fontFamily = Proxima,
                color = Color.White,
                fontSize = 14.sp
            )
        }
        Row(
            modifier = Modifier
                .padding(bottom = 10.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.developers),
                modifier = Modifier.weight(weight = .3f),
                color = Color.White.copy(alpha = .4f),
                fontFamily = Proxima,
                fontSize = 14.sp
            )
            Text(
                text = gameItem.gameDeveloper,
                modifier = Modifier.weight(weight = .7f),
                fontFamily = Proxima,
                color = Color.White,
                fontSize = 14.sp
            )
        }
    }
}