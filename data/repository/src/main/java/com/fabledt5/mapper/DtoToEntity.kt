package com.fabledt5.mapper

import com.fabledt5.db.entities.HotGameEntity
import com.fabledt5.domain.utlis.ESRBRatings
import com.fabledt5.domain.utlis.toPEGI
import com.fabledt5.remote.dto.list_of_games.GamesListResponse
import java.util.*

fun GamesListResponse.toEntity(): List<HotGameEntity> = results.map { result ->
    HotGameEntity(
        createdAt = Date().time,
        gameTitle = result.name,
        gamePoster = if (result.shortScreenshots.isNotEmpty())
            result.shortScreenshots.first().image
        else
            null,
        gamePEGIRating = result.esrbRating?.slug.toPEGI(),
        gameGenres = result.genres.take(n = 2).joinToString { it.name },
        releaseYear = result.released.take(n = 4)
    )
}