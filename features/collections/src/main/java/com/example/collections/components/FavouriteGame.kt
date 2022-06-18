package com.example.collections.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.fabledt5.domain.model.items.GameItem

@Composable
fun FavoriteGame(gameItem: GameItem) {

}

@Composable
fun DraggableImage(

) {

}

@Preview(showBackground = true, backgroundColor = 0xFF121214)
@Composable
fun FavoriteGamePreview() {
    FavoriteGame(
        gameItem = GameItem(
            gameId = -1,
            gameTitle = "The elder scrolls V: Skyrim"
        )
    )
}