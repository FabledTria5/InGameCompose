package com.fabledt5.repository.remote

import com.fabledt5.db.dao.HotGamesDao
import com.fabledt5.domain.model.GameItem
import com.fabledt5.domain.repository.GamesListRepository
import com.fabledt5.mapper.toDomain
import com.fabledt5.mapper.toDomainShort
import com.fabledt5.mapper.toEntity
import com.fabledt5.remote.api.ApiService
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class GamesListRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val hotGamesDao: HotGamesDao,
) : GamesListRepository {

    companion object {
        private const val TAG = "GamesListRepositoryImpl"
    }

    override suspend fun getHotGames(gamesCount: Int): List<GameItem> {
        val localGamesList = hotGamesDao.getHotGames()
        return if (localGamesList.isNotEmpty()) {
            if (System.currentTimeMillis() - localGamesList.first().createdAt >= TimeUnit.DAYS.convert(
                    7,
                    TimeUnit.MILLISECONDS
                )
            ) {
                localGamesList.toDomain()
            } else {
                hotGamesDao.clearHotGames()
                loadHotGamesFromRemote(gamesCount)
            }
        } else loadHotGamesFromRemote(gamesCount)
    }

    override suspend fun getMonthlyGames(
        dates: String,
        platformId: Int,
        gamesCount: Int
    ): List<GameItem> =
        apiService.getGamesByDates(pageSize = gamesCount, dates = dates, platforms = platformId)
            .toDomainShort()

    override suspend fun getBestGames(
        ratings: String,
        platformId: Int,
        gamesCount: Int
    ): List<GameItem> =
        apiService.getBestGames(
            pageSize = gamesCount,
            metacriticRatings = ratings,
            platforms = platformId
        ).toDomainShort()

    override suspend fun getNewGames(
        dates: String,
        platformId: Int,
        gamesCount: Int
    ): List<GameItem> =
        apiService.getGamesByDates(pageSize = gamesCount, dates = dates, platforms = platformId)
            .toDomainShort()

    private suspend fun loadHotGamesFromRemote(gamesCount: Int): List<GameItem> {
        val hotGamesResponse = apiService.getHotGamesList(pageSize = gamesCount)
        hotGamesDao.insertHotGames(hotGamesResponse.toEntity())
        return hotGamesDao.getHotGames().toDomain()
    }

}
