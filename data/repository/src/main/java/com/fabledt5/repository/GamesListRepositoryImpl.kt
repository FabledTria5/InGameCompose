package com.fabledt5.repository

import com.fabledt5.db.dao.HotGamesDao
import com.fabledt5.domain.model.GameItem
import com.fabledt5.domain.repository.GamesListRepository
import com.fabledt5.mapper.toDomain
import com.fabledt5.mapper.toEntity
import com.fabledt5.remote.GamesService
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class GamesListRepositoryImpl @Inject constructor(
    private val gamesService: GamesService,
    private val hotGamesDao: HotGamesDao
) : GamesListRepository {

    override suspend fun getHotGames(gamesCount: Int): List<GameItem> {
        val localGamesList = hotGamesDao.getHotGames()
        return if (localGamesList.isNotEmpty()) {
            if (System.currentTimeMillis() - localGamesList.first().createdAt < TimeUnit.DAYS.convert(
                    7,
                    TimeUnit.MILLISECONDS
                )
            ) {
                localGamesList.toDomain()
            } else {
                hotGamesDao.clearHotGames()
                loadGamesFromRemote(gamesCount)
            }
        } else loadGamesFromRemote(gamesCount)
    }

    private suspend fun loadGamesFromRemote(gamesCount: Int): List<GameItem> {
        val hotGamesResponse = gamesService.getHotGamesList(pageSize = gamesCount)
        hotGamesDao.insertHotGames(hotGamesResponse.toEntity())
        return hotGamesDao.getHotGames().toDomain()
    }

}
