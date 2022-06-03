package com.fabledt5.remote.api.dto.game_screenshots

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScreenshotsResult(
    @SerialName("hidden")
    val hidden: Boolean?,
    @SerialName("image")
    val image: String
)