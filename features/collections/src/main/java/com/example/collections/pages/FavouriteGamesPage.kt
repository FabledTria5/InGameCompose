package com.example.collections.pages

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.collections.components.EmptyGamesPage
import com.example.collections.components.FavoriteGame
import com.fabledt5.domain.model.Resource
import com.fabledt5.domain.model.items.GameItem

@ExperimentalMaterialApi
@Composable
fun FavouriteGamesPage(
    favoriteGames: Resource<List<GameItem>>,
    onGameClicked: (Int) -> Unit
) {
    when (favoriteGames) {
        Resource.Idle -> EmptyGamesPage()
        is Resource.Success -> ShowFavoriteGames(
            data = favoriteGames.data,
            onGameClicked = onGameClicked
        )
        else -> Unit
    }
}

@ExperimentalMaterialApi
@Composable
fun ShowFavoriteGames(
    data: List<GameItem>,
    onGameClicked: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 10.dp)
    ) {
        items(
            items = data,
            key = { it.gameId }
        ) { gameItem ->
            FavoriteGame(
                gameItem = gameItem,
                itemHeight = 180.dp,
                onGameClicked = { onGameClicked(gameItem.gameId) },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}