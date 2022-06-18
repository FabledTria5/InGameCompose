package com.fabledt5.mapper

import androidx.paging.PagingData
import androidx.paging.filter
import androidx.paging.map
import com.fabledt5.domain.model.items.GameItem
import com.fabledt5.domain.model.items.RatingItem
import com.fabledt5.domain.model.items.RequirementsItem
import com.fabledt5.domain.model.items.ReviewItem
import com.fabledt5.domain.utlis.setScale
import com.fabledt5.domain.utlis.toPEGI
import com.fabledt5.remote.api.dto.game_details.Platform
import com.fabledt5.remote.api.dto.game_screenshots.ScreenshotsResult
import com.fabledt5.remote.api.dto.game_trailers.GameTrailersResponse
import com.fabledt5.remote.api.dto.list_of_games.GamesListResponse
import com.fabledt5.remote.api.dto.list_of_games.GamesListResult
import com.fabledt5.remote.parser.dto.GameReviewDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

fun GamesListResponse.toDomainShort() = results.map { result ->
    GameItem(
        gameId = result.id,
        gamePoster = result.backgroundImage,
        gameTitle = result.name,
        gameReleaseYear = result.released?.take(n = 4) ?: "Unknown",
        gameLastUpdate = formatUpdateDate(result.updated),
        gameGenres = result.genres.joinToString(),
        gamePEGIRating = result.esrbRating?.slug.toPEGI()
    )
}

fun List<Platform>.toDomain() = try {
    val targetPlatform = first { it.platform.slug == "pc" }
    if (
        !targetPlatform.requirements.minimum.isNullOrEmpty() &&
        !targetPlatform.requirements.recommended.isNullOrEmpty()
    ) {
        RequirementsItem(
            min = targetPlatform.requirements.minimum?.removePrefix("Minimum:")!!,
            rec = targetPlatform.requirements.recommended?.removePrefix("Recommended:")!!
        )
    } else if (
        !targetPlatform.requirements.minimum.isNullOrEmpty() &&
        targetPlatform.requirements.recommended.isNullOrEmpty()
    ) {
        val requirements = targetPlatform.requirements.minimum?.split("Minimum: ")!!
        val minimumRequirements = requirements.first()
        val recommendedRequirements = requirements.last()

        RequirementsItem(min = minimumRequirements, rec = recommendedRequirements)
    } else null
} catch (e: Exception) {
    null
}

fun Flow<PagingData<GamesListResult>>.toDomain(): Flow<PagingData<GameItem>> =
    map { data ->
        data
            .filter { it.backgroundImage != null }
            .map { result ->
                GameItem(
                    gameId = result.id,
                    gamePoster = result.backgroundImage,
                    gameTitle = result.name,
                    gameReleaseYear = result.released?.run {
                        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                        val date = formatter.parse(this)
                        val newFormatter = SimpleDateFormat("dd MMM, yyyy", Locale.ENGLISH)
                        newFormatter.format(date!!)
                    } ?: "Unknown",
                    gameGenres = result.genres.take(n = 2).joinToString { it.name }
                )
            }
    }

fun List<ScreenshotsResult>.toDomain() = map { result ->
    result.image
}

fun List<GameReviewDto>.toDomain() = RatingItem(
    gameRating = getAverageRating(),
    gameReviews = filter { it.criticScore.isNotEmpty() }
        .shuffled()
        .map { dto ->
            ReviewItem(
                reviewerName = dto.criticName,
                reviewText = dto.reviewText,
                reviewerRating = dto.criticScore.toRating(),
                reviewDate = dto.reviewDate
            )
        }
)

fun GameTrailersResponse.toDomain(): String =
    results.lastOrNull { it.data.x480.isNotEmpty() }?.data?.x480 ?: ""

private fun formatUpdateDate(date: String): String {
    val localDate = LocalDate.parse(date.split('T')[0])
    return localDate.format(DateTimeFormatter.ofPattern("dd MMM. yyyy"))
}

private fun List<GameReviewDto>.getAverageRating(): String {
    var ratingsSum = 0
    this.forEach {
        if (it.criticScore.isNotEmpty())
            ratingsSum += it.criticScore.toRating()
    }
    return (ratingsSum.toDouble() / this.size).setScale(n = 1).toString()
}

private fun String.toRating() = if (this.toInt() > 90) 5 else this.toInt() / 20