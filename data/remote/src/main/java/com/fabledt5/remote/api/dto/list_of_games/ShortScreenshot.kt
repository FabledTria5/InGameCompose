package com.fabledt5.remote.api.dto.list_of_games

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ShortScreenshot(
    @SerialName("id")
    val id: Int,
    @SerialName("image")
    val image: String
)