package com.fabledt5.remote.dto.list_of_games

import com.google.gson.annotations.SerializedName

data class ShortScreenshot(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String
)