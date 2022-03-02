package com.fabledt5.domain.repository

import com.fabledt5.domain.model.PlatformItem
import kotlinx.coroutines.flow.Flow

interface PreferencesRepository {

    fun getFavoritePlatform(platformId: Int): Flow<PlatformItem>

    fun getFavoritePlatformId(): Flow<Int>

    suspend fun saveFavoritePlatform(platformId: Int)

}