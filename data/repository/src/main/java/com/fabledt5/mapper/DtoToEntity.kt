package com.fabledt5.mapper

import com.fabledt5.db.entities.HotGameEntity
import com.fabledt5.domain.utlis.toPEGI
import com.fabledt5.remote.api.dto.list_of_games.GamesListResponse
import java.util.*

fun GamesListResponse.toEntity(): List<HotGameEntity> = results.map { dto ->
    HotGameEntity(
        gameId = dto.id,
        createdAt = Date().time,
        gameTitle = dto.name,
        gamePoster = if (dto.shortScreenshots.isNotEmpty())
            dto.shortScreenshots.first().image
        else
            null,
        gamePEGIRating = dto.esrbRating?.slug.toPEGI(),
        gameGenres = dto.genres.take(n = 2).joinToString { it.name },
        releaseYear = dto.released.take(n = 4)
    )
}