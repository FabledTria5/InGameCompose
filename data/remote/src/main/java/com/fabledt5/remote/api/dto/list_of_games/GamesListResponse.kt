package com.fabledt5.remote.api.dto.list_of_games

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GamesListResponse(
    @SerialName("count")
    val count: Int,
    @SerialName("description")
    val description: String?,
    @SerialName("next")
    val next: String?,
    @SerialName("previous")
    val previous: String?,
    @SerialName("results")
    val results: List<Result>
)