package com.fabledt5.domain.use_case.search

import androidx.paging.PagingData
import com.fabledt5.domain.repository.SearchRepository
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

class SearchGames @Inject constructor(private val searchRepository: SearchRepository) {

    operator fun invoke(
        searchQuery: String,
        platforms: List<Int>,
        genres: List<Int>,
        developers: List<String>
    ) = searchRepository.searchGames(
        searchQuery = searchQuery,
        platforms = if (platforms.isNotEmpty()) platforms.joinToString() else null,
        genres = if (genres.isNotEmpty()) genres.joinToString() else null,
        developers = if (developers.isNotEmpty()) developers.joinToString { developerName ->
            developerName
                .lowercase()
                .replace(oldChar = ' ', newChar = '-')
        } else null
    ).catch {
        emit(PagingData.empty())
    }

}