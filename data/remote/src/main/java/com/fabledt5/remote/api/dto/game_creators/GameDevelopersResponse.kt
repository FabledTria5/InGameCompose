package com.fabledt5.remote.api.dto.game_creators

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameDevelopersResponse(
    @SerialName("count")
    val count: Int,
    @SerialName("next")
    val next: String?,
    @SerialName("previous")
    val previous: String?,
    @SerialName("results")
    val results: List<Result>
)