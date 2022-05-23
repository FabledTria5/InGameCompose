package com.fabledt5.mapper

import com.fabledt5.db.entities.DeveloperEntity
import com.fabledt5.db.entities.GenreEntity
import com.fabledt5.db.entities.HotGameEntity
import com.fabledt5.db.entities.PlatformEntity
import com.fabledt5.domain.utlis.toPEGI
import com.fabledt5.remote.api.dto.filters.DevelopersResponseItem
import com.fabledt5.remote.api.dto.filters.GenresResponseItem
import com.fabledt5.remote.api.dto.filters.PlatformsResponseItem
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

@JvmName("toEntityDevelopersResponseItem")
fun List<DevelopersResponseItem>.toEntity(): List<DeveloperEntity> = map { dto ->
    DeveloperEntity(
        developerName = dto.developerName,
        foundation = dto.developerFoundation,
        icon = dto.developerIcon,
        keyPeople = dto.developerKeyPeople,
        headquarters = dto.developerHeadquarters,
        preview = dto.developerPreview,
        website = dto.developerWebsite
    )
}

@JvmName("toEntityGenresResponseItem")
fun List<GenresResponseItem>.toEntity(): List<GenreEntity> = map { dto ->
    GenreEntity(genreId = dto.genreId, genreName = dto.genreName)
}

@JvmName("toEntityPlatformsResponseItem")
fun List<PlatformsResponseItem>.toEntity(): List<PlatformEntity> = map { dto ->
    PlatformEntity(
        platformId = dto.platformId,
        platformName = dto.platformName,
        platformImage = dto.platformImage
    )
}