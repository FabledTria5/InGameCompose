package com.fabledt5.remote.api.dto.game_details

import com.google.gson.annotations.SerializedName

data class Platform(
    @SerializedName("platform")
    val platform: PlatformItem,
    @SerializedName("released_at")
    val releasedAt: String,
    @SerializedName("requirements")
    val requirements: Requirements
)