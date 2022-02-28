package com.fabledt5.remote.dto.list_of_games

import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("added")
    val added: Int,
    @SerializedName("background_image")
    val backgroundImage: String,
    @SerializedName("dominant_color")
    val dominantColor: String,
    @SerializedName("esrb_rating")
    val esrbRating: EsrbRating?,
    @SerializedName("genres")
    val genres: List<Genre>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("metacritic")
    val metacritic: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("parent_platforms")
    val parentPlatforms: List<ParentPlatform>,
    @SerializedName("platforms")
    val platforms: List<Platform>,
    @SerializedName("playtime")
    val playtime: Int,
    @SerializedName("rating")
    val rating: Double,
    @SerializedName("rating_top")
    val ratingTop: Int,
    @SerializedName("ratings")
    val ratings: List<Rating>,
    @SerializedName("ratings_count")
    val ratingsCount: Int,
    @SerializedName("released")
    val released: String,
    @SerializedName("reviews_count")
    val reviewsCount: Int,
    @SerializedName("reviews_text_count")
    val reviewsTextCount: Int,
    @SerializedName("saturated_color")
    val saturatedColor: String,
    @SerializedName("short_screenshots")
    val shortScreenshots: List<ShortScreenshot>,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("suggestions_count")
    val suggestionsCount: Int,
    @SerializedName("tba")
    val tba: Boolean,
    @SerializedName("updated")
    val updated: String,
)