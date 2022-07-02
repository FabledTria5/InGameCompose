package com.fabledt5.remote.api.dto.list_of_games

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class ShortScreenshot(
    @SerialName("id")
    val id: Int,
    @SerialName("image")
    val image: String
)