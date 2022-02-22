package com.fabledt5.remote.dto.game_details

import com.google.gson.annotations.SerializedName

data class PlatformItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("slug")
    val slug: String
)