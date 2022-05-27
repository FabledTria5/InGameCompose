package com.fabledt5.remote.api.dto.game_details

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Platform(
    @SerialName("platform")
    val platform: PlatformDto,
    @SerialName("released_at")
    val releasedAt: String,
    @SerialName("requirements")
    val requirements: Requirements
)