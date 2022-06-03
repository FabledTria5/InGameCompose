package com.fabledt5.remote.api

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fabledt5.remote.api.dto.list_of_games.GamesListResult
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class SearchGamesPagingSource @AssistedInject constructor(
    private val gamesApi: GamesApi,
    @Assisted("searchQuery") private val searchQuery: String,
    @Assisted("developers") private val developers: String?,
    @Assisted("genres") private val genres: String?,
    @Assisted("platforms") private val platforms: String?
) : PagingSource<Int, GamesListResult>() {

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("searchQuery") searchQuery: String,
            @Assisted("developers") developers: String?,
            @Assisted("genres") genres: String?,
            @Assisted("platforms") platforms: String?
        ): SearchGamesPagingSource
    }

    companion object {
        const val INITIAL_PAGE = 1
        const val PAGE_SIZE = 15
    }

    override fun getRefreshKey(state: PagingState<Int, GamesListResult>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GamesListResult> {
        if (searchQuery.isBlank()) return LoadResult.Page(
            emptyList(),
            prevKey = null,
            nextKey = null
        )

        val page: Int = params.key ?: INITIAL_PAGE

        return try {
            val searchResponse = gamesApi.getGamesList(
                page = page,
                pageSize = PAGE_SIZE,
                search = searchQuery,
                platforms = platforms,
                developers = developers,
                genres = genres
            )

            val nextKey = searchResponse.next?.let { page + 1 }
            val prevKey = searchResponse.previous?.let { page - 1 }

            LoadResult.Page(data = searchResponse.results, nextKey = nextKey, prevKey = prevKey)
        } catch (e: Exception) {
            LoadResult.Error(throwable = e)
        }
    }

}