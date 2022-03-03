package com.fabledt5.remote.api.dto.game_trailers

import com.google.gson.annotations.SerializedName

data class GameTrailersResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String?,
    @SerializedName("previous")
    val previous: String?,
    @SerializedName("results")
    val results: List<Result>
)