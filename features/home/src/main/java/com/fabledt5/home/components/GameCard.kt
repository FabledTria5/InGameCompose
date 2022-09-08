package com.fabledt5.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.fabledt5.common.components.RemoteImage
import com.fabledt5.common.theme.DimGray
import com.fabledt5.domain.model.items.GameItem
import com.fabledt5.home.R

@Composable
fun GameCard(game: GameItem, modifier: Modifier = Modifier, onGameClick: (Int) -> Unit) {
    Card(
        modifier = modifier
            .height(100.dp)
            .fillMaxWidth(fraction = .49f)
            .clickable { onGameClick(game.gameId) },
        elevation = 10.dp,
        shape = RoundedCornerShape(size = 10.dp),
        backgroundColor = DimGray
    ) {
        RemoteImage(
            imagePath = game.gamePoster,
            contentDescription = stringResource(id = R.string.game_poster_template, game.gameTitle),
            contentScale = ContentScale.Crop
        )
    }
}