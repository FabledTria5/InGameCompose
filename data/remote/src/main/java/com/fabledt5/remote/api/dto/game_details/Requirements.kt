package com.fabledt5.remote.api.dto.game_details

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Requirements(
    @SerialName("minimum")
    val minimum: String?,
    @SerialName("recommended")
    val recommended: String?
)