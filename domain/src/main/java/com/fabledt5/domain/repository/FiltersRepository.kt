package com.fabledt5.domain.repository

import com.fabledt5.domain.model.DeveloperItem
import com.fabledt5.domain.model.GameGenre
import com.fabledt5.domain.model.PlatformItem
import kotlinx.coroutines.flow.Flow

interface FiltersRepository {

    fun getGameGenres(): Flow<List<GameGenre>>

    fun getGameDevelopers(): Flow<List<DeveloperItem>>

    fun getGamePlatforms(): Flow<List<PlatformItem>>

    fun getFavoritePlatform(): Flow<PlatformItem?>

    suspend fun setFavoritePlatform(platformId: Int)

}