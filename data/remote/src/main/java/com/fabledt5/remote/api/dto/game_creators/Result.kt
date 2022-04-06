package com.fabledt5.remote.api.dto.game_creators

import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("games_count")
    val gamesCount: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("image_background")
    val imageBackground: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("positions")
    val positions: List<Position>,
    @SerializedName("slug")
    val slug: String
)