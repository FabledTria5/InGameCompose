package com.fabledt5.domain.repository

import com.fabledt5.domain.model.items.DeveloperItem
import com.fabledt5.domain.model.items.GenreItem
import com.fabledt5.domain.model.items.PlatformItem
import kotlinx.coroutines.flow.Flow

interface FiltersRepository {

    fun getGameGenres(): Flow<List<GenreItem>>

    fun getGameDevelopers(): Flow<List<DeveloperItem>>

    fun getGamePlatforms(): Flow<List<PlatformItem>>

    fun getFavoritePlatform(): Flow<PlatformItem>

    suspend fun setFavoritePlatform(platformId: Int)

}