package com.fabledt5.domain.repository

import com.fabledt5.domain.model.DeveloperItem
import com.fabledt5.domain.model.GameGenre
import com.fabledt5.domain.model.PlatformItem
import kotlinx.coroutines.flow.Flow

interface FiltersRepository {

    suspend fun fetchDevelopersList()

    suspend fun fetchGenresList()

    suspend fun fetchPlatformsList()

    fun getGameGenres(): Flow<List<GameGenre>>

    fun getGameDevelopers(): Flow<List<DeveloperItem>>

    fun getGamePlatforms(): Flow<List<PlatformItem>>

}