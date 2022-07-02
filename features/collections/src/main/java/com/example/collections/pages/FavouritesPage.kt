package com.example.collections.pages

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.collections.components.FavoriteGame
import com.fabledt5.domain.model.items.GameItem

@ExperimentalMaterialApi
@Composable
fun FavouritesPage() {
    val posters = listOf(
        "https://www.cyberpunk.net/build/images/home3/screen-image-about-08a097de.jpg",
        "https://newxboxone.ru/wp-content/uploads/2022/01/305f04c9-8780-4c7b-91dd-e87979844dab.jpg",
        "https://www.soyuz.ru/public/uploads/files/2/7479906/2022030810110705df35b2e4.jpg"
    )
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(15) {
            FavoriteGame(
                gameItem = GameItem(
                    gameId = -1,
                    gamePoster = posters.random(),
                    gameTitle = "The elder scrolls V: skyrim"
                ),
                itemHeight = 180.dp,
                onAddedToFavoritesClicked = {},
                onShareWithFriendsClicked = {}
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}