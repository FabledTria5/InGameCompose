package com.fabledt5.remote.api.dto.game_trailers

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("max")
    val max: String,
    @SerializedName("480")
    val x480: String
)