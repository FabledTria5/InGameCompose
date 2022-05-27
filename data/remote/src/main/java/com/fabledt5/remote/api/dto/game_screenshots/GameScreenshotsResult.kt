package com.fabledt5.remote.api.dto.game_screenshots

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameScreenshotsResult(
    @SerialName("count")
    val count: Int,
    @SerialName("next")
    val next: String?,
    @SerialName("previous")
    val previous: String?,
    @SerialName("results")
    val results: List<Result>
)