package com.fabledt5.mapper

import com.fabledt5.db.entities.*
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
            releaseDate = entity.releaseYear,
        )
    }
}

@JvmName("toDomainSavedGameEntity")
fun Flow<List<SavedGameEntity>>.toDomain(): Flow<List<GameItem>> = map { list ->
    list.map { savedGameEntity ->
        GameItem(
            gameId = savedGameEntity.gameId,
            gamePoster = savedGameEntity.gamePoster,
            gameTitle = savedGameEntity.gameTitle,
            gameDeveloper = savedGameEntity.gameDeveloper,
            releaseDate = savedGameEntity.releaseDate
        )
    }
}

@JvmName("toDomainPlatformEntity")
fun Flow<PlatformEntity>.toDomain(): Flow<PlatformItem> = map { entity ->
    PlatformItem(
        platformId = entity.platformId,
        platformName = entity.platformName,
        platformImage = entity.platformImage
    )
}

@JvmName("toDomainPlatformEntityList")
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