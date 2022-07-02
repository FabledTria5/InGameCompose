package com.fabledt5.remote.api.dto.game_details

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class GameDetailsResponse(
    @SerialName("added")
    val added: Int,
    @SerialName("alternative_names")
    val alternativeNames: List<String>,
    @SerialName("background_image")
    val backgroundImage: String,
    @SerialName("creators_count")
    val creatorsCount: Int,
    @SerialName("description")
    val description: String,
    @SerialName("description_raw")
    val descriptionRaw: String,
    @SerialName("developers")
    val developers: List<Developer>,
    @SerialName("dominant_color")
    val dominantColor: String,
    @SerialName("esrb_rating")
    val esrbRating: EsrbRating?,
    @SerialName("game_series_count")
    val gameSeriesCount: Int,
    @SerialName("genres")
    val genres: List<Genre>,
    @SerialName("id")
    val id: Int,
    @SerialName("metacritic")
    val metacritic: Int?,
    @SerialName("metacritic_url")
    val metacriticUrl: String,
    @SerialName("movies_count")
    val moviesCount: Int,
    @SerialName("name")
    val name: String,
    @SerialName("name_original")
    val nameOriginal: String,
    @SerialName("parent_achievements_count")
    val parentAchievementsCount: Int,
    @SerialName("parents_count")
    val parentsCount: Int,
    @SerialName("platforms")
    val platforms: List<Platform>,
    @SerialName("playtime")
    val playtime: Int,
    @SerialName("publishers")
    val publishers: List<Publisher>,
    @SerialName("rating")
    val rating: Double,
    @SerialName("rating_top")
    val ratingTop: Int,
    @SerialName("ratings")
    val ratings: List<Rating>,
    @SerialName("ratings_count")
    val ratingsCount: Int,
    @SerialName("released")
    val released: String?,
    @SerialName("reviews_count")
    val reviewsCount: Int,
    @SerialName("reviews_text_count")
    val reviewsTextCount: Int,
    @SerialName("screenshots_count")
    val screenshotsCount: Int,
    @SerialName("slug")
    val slug: String,
    @SerialName("suggestions_count")
    val suggestionsCount: Int,
)