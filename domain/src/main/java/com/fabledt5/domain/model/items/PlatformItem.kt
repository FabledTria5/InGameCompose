package com.fabledt5.domain.model.items

data class PlatformItem(
    val platformId: Int = -1,
    val platformName: String = "",
    val platformImage: String = "",
    val isFavorite: Boolean = false
)