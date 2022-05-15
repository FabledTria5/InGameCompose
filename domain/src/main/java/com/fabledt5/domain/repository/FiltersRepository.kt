package com.fabledt5.domain.repository

import com.fabledt5.domain.model.DeveloperItem
import com.fabledt5.domain.model.GameGenre
import kotlinx.coroutines.flow.Flow

interface FiltersRepository {

    fun getDevelopersList(): Flow<List<DeveloperItem>>

    fun getGenresList(): Flow<List<GameGenre>>

}