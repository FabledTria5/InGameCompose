package com.fabledt5.remote.api.dto.game_trailers

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Result(
    @SerialName("data")
    val `data`: Data,
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("preview")
    val preview: String
)