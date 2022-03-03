package com.fabledt5.remote.api.dto.list_of_games

import com.google.gson.annotations.SerializedName

data class GamesListResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("next")
    val next: String?,
    @SerializedName("previous")
    val previous: String?,
    @SerializedName("results")
    val results: List<Result>
)