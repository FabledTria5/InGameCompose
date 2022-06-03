package com.fabledt5.domain.repository

import androidx.paging.PagingData
import com.fabledt5.domain.model.items.GameItem
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    fun searchGames(
        searchQuery: String,
        platforms: String?,
        genres: String?,
        developers: String?
    ): Flow<PagingData<GameItem>>

}