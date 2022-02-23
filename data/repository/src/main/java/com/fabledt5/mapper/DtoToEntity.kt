package com.fabledt5.mapper

import com.fabledt5.db.entities.HotGameEntity
import com.fabledt5.remote.dto.list_of_games.GamesListResponse
import java.util.*

fun GamesListResponse.toEntity(): List<HotGameEntity> = results.map { result ->
    HotGameEntity(
        createdAt = Date().time,
        gameTitle = result.name,
        gamePoster = if (result.shortScreenshots.isNotEmpty()) result.shortScreenshots.first().image else null,
        gamePEGIRating = pickRating(result.esrbRating.slug),
        gameGenres = result.genres.take(n = 2).joinToString { it.name },
        releaseYear = result.released
    )
}

private fun pickRating(slug: String) = when (slug) {
    ESRBRatings.everyone.name -> "7+"
    ESRBRatings.`everyone-10-plus`.name -> "12+"
    ESRBRatings.teen.name -> "16+"
    ESRBRatings.mature.name -> "16+"
    ESRBRatings.`adults-only`.name -> "18+"
    else -> "16+"
}

@Suppress("EnumEntryName")
enum class ESRBRatings {
    everyone, `everyone-10-plus`, teen, mature, `adults-only`
}