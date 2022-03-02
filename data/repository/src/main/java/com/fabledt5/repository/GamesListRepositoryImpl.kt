package com.fabledt5.repository

import com.fabledt5.db.dao.HotGamesDao
import com.fabledt5.db.dao.PlatformsDao
import com.fabledt5.domain.model.GameItem
import com.fabledt5.domain.model.PlatformItem
import com.fabledt5.domain.repository.GamesListRepository
import com.fabledt5.mapper.toDomain
import com.fabledt5.mapper.toDomainShort
import com.fabledt5.mapper.toEntity
import com.fabledt5.remote.GamesService
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class GamesListRepositoryImpl @Inject constructor(
    private val gamesService: GamesService,
    private val hotGamesDao: HotGamesDao,
    private val platformsDao: PlatformsDao
) : GamesListRepository {

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

    override suspend fun getMonthlyGames(dates: String, gamesCount: Int): List<GameItem> =
        gamesService.getGamesByDates(pageSize = gamesCount, dates = dates).toDomainShort()

    override suspend fun getBestGames(ratings: String, gamesCount: Int): List<GameItem> =
        gamesService.getBestGames(pageSize = gamesCount, metacriticRatings = ratings)
            .toDomainShort()

    override suspend fun getNewGames(dates: String, gamesCount: Int): List<GameItem> =
        gamesService.getGamesByDates(pageSize = gamesCount, dates = dates).toDomainShort()

    override suspend fun getPlatformsList(): List<PlatformItem> {
        val localPlatformsList = platformsDao.getPlatformsList()
        return if (!localPlatformsList.isNullOrEmpty()) localPlatformsList.toDomain()
        else {
            val remotePlatformsList = gamesService.getGamePlatforms()
            platformsDao.insertPlatforms(platforms = remotePlatformsList.toEntity())
            platformsDao.getPlatformsList().toDomain()
        }
    }

    private suspend fun loadHotGamesFromRemote(gamesCount: Int): List<GameItem> {
        val hotGamesResponse = gamesService.getHotGamesList(pageSize = gamesCount)
        hotGamesDao.insertHotGames(hotGamesResponse.toEntity())
        return hotGamesDao.getHotGames().toDomain()
    }

}
