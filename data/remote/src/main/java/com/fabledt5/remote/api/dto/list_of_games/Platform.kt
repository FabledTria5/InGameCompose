package com.fabledt5.remote.api.dto.list_of_games

import com.google.gson.annotations.SerializedName

data class Platform(
    @SerializedName("platform")
    val platform: PlatformItem,
    @SerializedName("released_at")
    val releasedAt: String?,
    @SerializedName("requirements_en")
    val requirementsEn: Any?,
    @SerializedName("requirements_ru")
    val requirementsRu: Any?
)