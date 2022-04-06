package com.fabledt5.remote.api.dto.game_details


import com.google.gson.annotations.SerializedName

data class Rating(
    @SerializedName("count")
    val count: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("percent")
    val percent: Double,
    @SerializedName("title")
    val title: String
)