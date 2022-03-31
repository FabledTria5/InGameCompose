package com.fabledt5.game.pages

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fabledt5.common.theme.Proxima
import com.fabledt5.domain.model.GameItem
import com.fabledt5.game.R

@Composable
fun InfoGamePage(gameItem: GameItem) {
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = stringResource(R.string.release_date),
                modifier = Modifier
                    .weight(weight = .3f)
                    .padding(end = 15.dp),
                color = Color.White.copy(alpha = .5f),
                fontFamily = Proxima,
                fontSize = 14.sp
            )
            Text(
                text = gameItem.gameReleaseYear,
                modifier = Modifier.weight(weight = .7f),
                fontFamily = Proxima,
                color = Color.White
            )
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = stringResource(R.string.directors),
                modifier = Modifier
                    .weight(weight = .3f)
                    .padding(end = 15.dp),
                color = Color.White.copy(alpha = .5f),
                fontFamily = Proxima,
                fontSize = 14.sp
            )
            Text(
                text = gameItem.gameDirectors,
                modifier = Modifier.weight(weight = .7f),
                fontFamily = Proxima,
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    InfoGamePage(
        gameItem = GameItem(
            gameId = 111,
            gameReleaseYear = "May 19, 2015",
            gameDirectors = "Koward Tomzakisowitch, Sebastian Steaven, Meituzer Kanikt"
        )
    )
}