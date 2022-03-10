package com.fabledt5.mapper

import com.fabledt5.domain.model.GameItem
import com.fabledt5.domain.model.GameRequirements
import com.fabledt5.domain.model.ReviewItem
import com.fabledt5.domain.utlis.toDate
import com.fabledt5.domain.utlis.toPEGI
import com.fabledt5.remote.api.dto.game_details.Platform
import com.fabledt5.remote.api.dto.game_details.Requirements
import com.fabledt5.remote.api.dto.game_screenshots.Result
import com.fabledt5.remote.api.dto.game_trailers.GameTrailersResponse
import com.fabledt5.remote.api.dto.list_of_games.GamesListResponse
import com.fabledt5.remote.parser.dto.GameReviewDto

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

fun List<Platform>.toDomain() = try {
    val targetPlatform = first { it.platform.slug == "pc" }
    if (targetPlatform.requirements.minimum != null && targetPlatform.requirements.recommended != null) {
        GameRequirements(
            min = targetPlatform.requirements.minimum!!,
            rec = targetPlatform.requirements.recommended!!
        )
    } else null
} catch (e: NoSuchElementException) {
    null
}

fun List<Result>.toDomain() = map { result ->
    result.image
}

@JvmName("toDomainGameReviewDto")
fun List<GameReviewDto>.toDomain(): List<ReviewItem> = map { dto ->
    ReviewItem(
        reviewerName = dto.criticName,
        reviewText = dto.reviewText,
        reviewerRating = if (dto.criticScore.toInt() > 90) 5 else dto.criticScore.toInt() / 20,
        reviewDate = dto.reviewDate.toDate()
    )
}

fun GameTrailersResponse.toDomain(): String? = results.firstOrNull()?.data?.max