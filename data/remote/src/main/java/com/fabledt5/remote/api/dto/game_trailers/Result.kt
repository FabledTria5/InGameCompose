package com.fabledt5.remote.api.dto.game_trailers

import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("preview")
    val preview: String
)