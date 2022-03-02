package com.fabledt5.mapper

import com.fabledt5.db.entities.HotGameEntity
import com.fabledt5.db.entities.PlatformEntity
import com.fabledt5.domain.model.GameItem
import com.fabledt5.domain.model.PlatformItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@JvmName("toDomainHotGameEntity")
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

@JvmName("toDomainPlatformEntity")
fun List<PlatformEntity>.toDomain(): List<PlatformItem> = map { entity ->
    PlatformItem(platformId = entity.platformId, platformName = entity.platformName)
}

fun Flow<PlatformEntity>.toDomain(): Flow<PlatformItem> = map { entity ->
    PlatformItem(platformId = entity.platformId, platformName = entity.platformName)
}