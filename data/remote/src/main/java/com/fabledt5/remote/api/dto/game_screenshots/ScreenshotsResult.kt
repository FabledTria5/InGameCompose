package com.fabledt5.remote.api.dto.game_screenshots

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class ScreenshotsResult(
    @SerialName("hidden")
    val hidden: Boolean?,
    @SerialName("image")
    val image: String
)