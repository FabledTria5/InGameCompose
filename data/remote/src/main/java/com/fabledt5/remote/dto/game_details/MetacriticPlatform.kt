package com.fabledt5.remote.dto.game_details

import com.google.gson.annotations.SerializedName

data class MetacriticPlatform(
    @SerializedName("metascore")
    val metascore: Int,
    @SerializedName("url")
    val url: String
)