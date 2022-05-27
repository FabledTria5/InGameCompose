package com.fabledt5.remote.api.dto.list_of_games

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ParentPlatform(
    @SerialName("platform")
    val platform: Platform
)