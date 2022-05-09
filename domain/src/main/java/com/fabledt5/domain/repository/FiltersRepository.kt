package com.fabledt5.domain.repository

import com.fabledt5.domain.model.DeveloperItem
import kotlinx.coroutines.flow.Flow

interface FiltersRepository {

    fun getDevelopersList(): Flow<List<DeveloperItem>>

}