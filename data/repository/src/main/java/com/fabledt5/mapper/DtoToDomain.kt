package com.fabledt5.mapper

import com.fabledt5.domain.model.GameItem
import com.fabledt5.domain.utlis.toPEGI
import com.fabledt5.remote.dto.list_of_games.GamesListResponse

fun GamesListResponse.toDomainShort() = results.map { result ->
    GameItem(
        gameId = result.id,
        gamePoster = result.backgroundImage,
        gameTitle = result.name,
        gameReleaseYear = result.released.take(n = 4),
        gameGenres = result.genres.joinToString(),
        gamePEGIRating = result.esrbRating?.slug.toPEGI()
    )
}