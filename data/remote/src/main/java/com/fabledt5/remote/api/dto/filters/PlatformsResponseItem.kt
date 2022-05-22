package com.fabledt5.remote.api.dto.filters

import kotlinx.serialization.Serializable

@Serializable
data class PlatformsResponseItem(
    val platformId: Int,
    val platformImage: String,
    val platformName: String
)