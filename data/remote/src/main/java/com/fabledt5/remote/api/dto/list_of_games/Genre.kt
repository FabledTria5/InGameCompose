package com.fabledt5.remote.api.dto.list_of_games

import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("games_count")
    val gamesCount: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image_background")
    val imageBackground: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("slug")
    val slug: String
)