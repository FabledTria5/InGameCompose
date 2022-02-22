package com.fabledt5.mapper

import com.fabledt5.db.entities.HotGameEntity
import com.fabledt5.domain.model.GameItem

fun List<HotGameEntity>.toDomain(): List<GameItem> = map { entity ->
    GameItem(
        gameId = entity.id,
        gamePoster = entity.gamePoster,
        gameTitle = entity.gameTitle,
        gameGenres = entity.gameGenres,
        gamePEGIRating = entity.gamePEGIRating,
        gameReleaseYear = entity.releaseYear,
    )
}