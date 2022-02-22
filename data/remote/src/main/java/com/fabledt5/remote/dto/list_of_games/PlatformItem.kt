package com.fabledt5.remote.dto.list_of_games

import com.google.gson.annotations.SerializedName

data class PlatformItem(
    @SerializedName("games_count")
    val gamesCount: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: Any?,
    @SerializedName("image_background")
    val imageBackground: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("year_end")
    val yearEnd: Any?,
    @SerializedName("year_start")
    val yearStart: Any?
)