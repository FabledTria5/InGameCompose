package com.fabledt5.remote.api.dto.list_of_games

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
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
    val results: List<GamesListResult>
)