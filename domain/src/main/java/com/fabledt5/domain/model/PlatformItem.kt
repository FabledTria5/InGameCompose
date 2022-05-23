package com.fabledt5.domain.model

data class PlatformItem(
    val platformId: Int,
    val platformName: String,
    val platformImage: String,
    val isFavorite: Boolean = false
)