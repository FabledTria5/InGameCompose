package com.fabledt5.remote.dto.game_screenshots

import com.google.gson.annotations.SerializedName

data class GameScreenshotsResult(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String?,
    @SerializedName("previous")
    val previous: String?,
    @SerializedName("results")
    val results: List<Result>
)