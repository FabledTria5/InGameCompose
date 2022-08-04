package com.fabledt5.mapper

import com.fabledt5.db.entities.SavedGameEntity
import com.fabledt5.domain.model.items.GameItem

fun GameItem.toEntity() = SavedGameEntity(
    gameId = gameId,
    gameTitle = gameTitle,
    gamePoster = gamePoster,
    gameDeveloper = gameDeveloper,
    releaseDate = releaseDate
)