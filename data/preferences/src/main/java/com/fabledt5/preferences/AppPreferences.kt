package com.fabledt5.preferences

import kotlinx.coroutines.flow.Flow

interface AppPreferences {

    val readFavoritePlatform: Flow<Int>

    suspend fun persistFavoritePlatform(platformId: Int)

}