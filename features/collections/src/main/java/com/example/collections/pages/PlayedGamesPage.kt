package com.example.collections.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.collections.R
import com.example.collections.components.EmptyGamesPage
import com.fabledt5.common.components.SharedGameItem
import com.fabledt5.common.theme.Mark
import com.fabledt5.domain.model.Resource
import com.fabledt5.domain.model.items.GameItem

@Composable
fun PlayedGamesPage(
    playedGames: Resource<List<GameItem>>,
    onGameClicked: (Int) -> Unit,
    onAddToFavoriteClicked: (GameItem) -> Unit
) {
    when (playedGames) {
        Resource.Idle -> EmptyGamesPage()
        is Resource.Success -> ShowPlayedGames(
            data = playedGames.data,
            onGameClicked = onGameClicked,
            onAddToFavoriteClicked = onAddToFavoriteClicked
        )
        else -> Unit
    }
}

@Composable
fun ShowPlayedGames(
    data: List<GameItem>,
    onGameClicked: (Int) -> Unit,
    onAddToFavoriteClicked: (GameItem) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(), contentPadding = PaddingValues(horizontal = 10.dp)
    ) {
        items(
            items = data,
            key = { it.gameId }
        ) { gameItem ->
            SharedGameItem(
                gameItem = gameItem,
                onGameClicked = { onGameClicked(gameItem.gameId) },
                gameAction = {
                    Text(
                        text = stringResource(R.string.add_to_favorite),
                        modifier = Modifier
                            .weight(.1f)
                            .clickable { onAddToFavoriteClicked(gameItem) },
                        color = Color.White.copy(alpha = .3f),
                        fontFamily = Mark,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        overflow = TextOverflow.Ellipsis
                    )
                })
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}
