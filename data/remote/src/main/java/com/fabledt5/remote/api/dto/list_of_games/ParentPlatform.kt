package com.fabledt5.remote.api.dto.list_of_games


import com.google.gson.annotations.SerializedName

data class ParentPlatform(
    @SerializedName("platform")
    val platform: Platform
)