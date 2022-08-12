package com.fabledt5.remote.api.dto.game_details

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Requirements(
    @SerialName("minimum")
    val minimum: String?,
    @SerialName("recommended")
    val recommended: String?
)