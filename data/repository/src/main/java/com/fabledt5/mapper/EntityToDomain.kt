package com.fabledt5.mapper

import com.fabledt5.db.entities.DeveloperEntity
import com.fabledt5.db.entities.GenreEntity
import com.fabledt5.db.entities.HotGameEntity
import com.fabledt5.db.entities.PlatformEntity
import com.fabledt5.domain.model.items.DeveloperItem
import com.fabledt5.domain.model.items.GameItem
import com.fabledt5.domain.model.items.GenreItem
import com.fabledt5.domain.model.items.PlatformItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@JvmName("toDomainHotGameEntity")
fun Flow<List<HotGameEntity>>.toDomain(): Flow<List<GameItem>> = map { list ->
    list.map { entity ->
        GameItem(
            gameId = entity.gameId,
            gamePoster = entity.gamePoster,
            gameTitle = entity.gameTitle,
            gameGenres = entity.gameGenres,
            gamePEGIRating = entity.gamePEGIRating,
            gameReleaseYear = entity.releaseYear,
        )
    }
}

fun Flow<PlatformEntity?>.toDomain(): Flow<PlatformItem?> = map { entity ->
    entity?.let {
        PlatformItem(
            platformId = it.platformId,
            platformName = it.platformName,
            platformImage = it.platformImage
        )
    }
}

@JvmName("toDomainPlatformEntity")
fun Flow<List<PlatformEntity>>.toDomain(): Flow<List<PlatformItem>> = map { list ->
    list.map { entity ->
        PlatformItem(
            platformId = entity.platformId,
            platformName = entity.platformName,
            platformImage = entity.platformImage
        )
    }
}

@JvmName("toDomainDeveloperEntity")
fun Flow<List<DeveloperEntity>>.toDomain(): Flow<List<DeveloperItem>> = map { list ->
    list.map { entity ->
        DeveloperItem(
            foundation = entity.foundation,
            icon = entity.icon,
            keyPeople = entity.keyPeople,
            headquarters = entity.headquarters,
            developerName = entity.developerName,
            preview = entity.preview,
            website = entity.website
        )
    }
}

@JvmName("toDomainGenreEntity")
fun Flow<List<GenreEntity>>.toDomain(): Flow<List<GenreItem>> = map { list ->
    list.map { entity ->
        GenreItem(
            genreId = entity.genreId,
            genreTitle = entity.genreName
        )
    }
}