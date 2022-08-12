package com.fabledt5.repository.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.fabledt5.domain.repository.SearchRepository
import com.fabledt5.mapper.toDomain
import com.fabledt5.remote.api.SearchGamesPagingSource
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchGamesPagingSource: SearchGamesPagingSource.Factory,
) : SearchRepository {

    override fun searchGames(
        searchQuery: String,
        platforms: String?,
        genres: String?,
        developers: String?
    ) = Pager(PagingConfig(pageSize = 15)) {
        searchGamesPagingSource.create(searchQuery, developers, genres, platforms)
    }.flow.toDomain()

}


