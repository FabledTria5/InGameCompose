package com.fabledt5.remote.api.dto.game_creators

import com.google.gson.annotations.SerializedName

data class Position(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("slug")
    val slug: String
)