package com.fabledt5.remote.api.dto.game_details

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Rating(
    @SerialName("count")
    val count: Int,
    @SerialName("id")
    val id: Int,
    @SerialName("percent")
    val percent: Double,
    @SerialName("title")
    val title: String
)