package com.fabledt5.mapper

import androidx.paging.PagingData
import androidx.paging.filter
import androidx.paging.map
import com.fabledt5.domain.model.items.GameItem
import com.fabledt5.domain.model.items.RatingItem
import com.fabledt5.domain.model.items.RequirementsItem
import com.fabledt5.domain.model.items.ReviewItem
import com.fabledt5.domain.utlis.toPEGI
import com.fabledt5.remote.api.dto.game_details.Platform
import com.fabledt5.remote.api.dto.game_screenshots.GameScreenshotsResult
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
        releaseDate = result.released?.formatReleaseDate().orEmpty(),
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
                    releaseDate = result.released?.formatReleaseDate().orEmpty(),
                    gameGenres = result.genres.take(n = 2).joinToString { it.name }
                )
            }
    }

fun GameScreenshotsResult.toDomain() = results.map { result ->
    result.image
}

fun List<GameReviewDto>.toDomain() = RatingItem(
    gameRating = (sumOf { it.criticScore } / size).toString(),
    gameReviews = shuffled()
        .map { dto ->
            ReviewItem(
                reviewerName = dto.criticName,
                reviewText = dto.reviewText,
                reviewerRating = dto.criticScore.toRating(),
                reviewDate = dto.reviewDate
            )
        }
)

private fun String.formatReleaseDate(): String {
    val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
    val date = formatter.parse(this)
    val newFormatter = SimpleDateFormat("dd MMM, yyyy", Locale.ENGLISH)
    return newFormatter.format(date!!)
}

private fun formatUpdateDate(date: String): String {
    val localDate = LocalDate.parse(date.split('T')[0])
    return localDate.format(DateTimeFormatter.ofPattern("dd MMM. yyyy"))
}

private fun Int.toRating() =
    1 + (this > 30).toInt() + (this > 59).toInt() + (this > 74).toInt() + (this > 89).toInt()

private fun Boolean.toInt() = if (this) 1 else 0
