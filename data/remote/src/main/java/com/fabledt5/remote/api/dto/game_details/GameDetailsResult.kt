package com.fabledt5.remote.api.dto.game_details

import com.google.gson.annotations.SerializedName

data class GameDetailsResult(
    @SerializedName("achievements_count")
    val achievementsCount: Int,
    @SerializedName("added")
    val added: Int,
    @SerializedName("additions_count")
    val additionsCount: Int,
    @SerializedName("alternative_names")
    val alternativeNames: List<String>,
    @SerializedName("background_image")
    val backgroundImage: String,
    @SerializedName("background_image_additional")
    val backgroundImageAdditional: String,
    @SerializedName("creators_count")
    val creatorsCount: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("esrb_rating")
    val esrbRating: EsrbRating,
    @SerializedName("game_series_count")
    val gameSeriesCount: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("metacritic")
    val metacritic: Int,
    @SerializedName("metacritic_platforms")
    val metacriticPlatforms: List<MetacriticPlatform>,
    @SerializedName("metacritic_url")
    val metacriticUrl: String,
    @SerializedName("movies_count")
    val moviesCount: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("name_original")
    val nameOriginal: String,
    @SerializedName("parent_achievements_count")
    val parentAchievementsCount: String,
    @SerializedName("parents_count")
    val parentsCount: Int,
    @SerializedName("platforms")
    val platforms: List<Platform>,
    @SerializedName("playtime")
    val playtime: Int,
    @SerializedName("rating")
    val rating: Int,
    @SerializedName("rating_top")
    val ratingTop: Int,
    @SerializedName("ratings_count")
    val ratingsCount: Int,
    @SerializedName("reddit_count")
    val redditCount: Int,
    @SerializedName("reddit_description")
    val redditDescription: String,
    @SerializedName("reddit_logo")
    val redditLogo: String,
    @SerializedName("reddit_name")
    val redditName: String,
    @SerializedName("reddit_url")
    val redditUrl: String,
    @SerializedName("released")
    val released: String,
    @SerializedName("reviews_text_count")
    val reviewsTextCount: String,
    @SerializedName("screenshots_count")
    val screenshotsCount: Int,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("suggestions_count")
    val suggestionsCount: Int,
    @SerializedName("tba")
    val tba: Boolean,
    @SerializedName("twitch_count")
    val twitchCount: String,
    @SerializedName("updated")
    val updated: String,
    @SerializedName("website")
    val website: String,
    @SerializedName("youtube_count")
    val youtubeCount: String
)