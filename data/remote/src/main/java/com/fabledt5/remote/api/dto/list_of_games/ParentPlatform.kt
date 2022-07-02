package com.fabledt5.remote.api.dto.list_of_games

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class ParentPlatform(
    @SerialName("platform")
    val platform: Platform
)