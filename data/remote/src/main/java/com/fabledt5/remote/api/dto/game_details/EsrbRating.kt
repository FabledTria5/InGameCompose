package com.fabledt5.remote.api.dto.game_details

import com.google.gson.annotations.SerializedName

data class EsrbRating(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("slug")
    val slug: String?
)