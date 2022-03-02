package com.fabledt5.domain.repository

import com.fabledt5.domain.model.PlatformItem
import kotlinx.coroutines.flow.Flow

interface PreferencesRepository {

    suspend fun persistPrimaryPlatform(platform: PlatformItem)

    fun readFavoritePlatforms(): Flow<PlatformItem>

}