package com.fabledt5.remote.api.dto.game_screenshots

import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("hidden")
    val hidden: Boolean,
    @SerializedName("image")
    val image: String
)