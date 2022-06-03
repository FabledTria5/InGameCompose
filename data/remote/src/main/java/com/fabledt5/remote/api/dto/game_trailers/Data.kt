package com.fabledt5.remote.api.dto.game_trailers

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Data(
    @SerialName("max")
    val max: String,
    @SerialName("480")
    val x480: String
)