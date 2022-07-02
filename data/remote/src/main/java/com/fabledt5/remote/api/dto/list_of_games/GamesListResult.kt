package com.fabledt5.remote.api.dto.list_of_games

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class GamesListResult(
    @SerialName("added")
    val added: Int,
    @SerialName("background_image")
    val backgroundImage: String?,
    @SerialName("dominant_color")
    val dominantColor: String,
    @SerialName("esrb_rating")
    val esrbRating: EsrbRating?,
    @SerialName("genres")
    val genres: List<Genre>,
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("parent_platforms")
    val parentPlatforms: List<ParentPlatform>,
    @SerialName("platforms")
    val platforms: List<Platform>,
    @SerialName("playtime")
    val playtime: Int,
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
    @SerialName("saturated_color")
    val saturatedColor: String,
    @SerialName("short_screenshots")
    val shortScreenshots: List<ShortScreenshot>,
    @SerialName("slug")
    val slug: String,
    @SerialName("suggestions_count")
    val suggestionsCount: Int,
    @SerialName("tba")
    val tba: Boolean,
    @SerialName("updated")
    val updated: String,
)