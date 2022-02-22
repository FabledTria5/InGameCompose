package com.fabledt5.remote.dto.game_creators

import com.google.gson.annotations.SerializedName

data class GameCreatorsResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String?,
    @SerializedName("previous")
    val previous: String?,
    @SerializedName("results")
    val results: List<Result>
)