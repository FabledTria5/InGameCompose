package com.fabledt5.repository.remote

import com.fabledt5.db.dao.GamesDao
import com.fabledt5.domain.model.GameType
import com.fabledt5.domain.model.items.GameItem
import com.fabledt5.domain.repository.GamesListRepository
import com.fabledt5.mapper.toDomain
import com.fabledt5.mapper.toDomainShort
import com.fabledt5.mapper.toEntity
import com.fabledt5.remote.api.GamesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import java.util.*
import javax.inject.Inject

class GamesListRepositoryImpl @Inject constructor(
    private val gamesApi: GamesApi,
    private val gamesDao: GamesDao,
) : GamesListRepository {

    override fun getHotGames(
        gamesCount: Int,
        dates: String,
        metacriticRatings: String
    ): Flow<List<GameItem>> = gamesDao.getGames(GameType.HOT_GAME.ordinal)
        .onEach { list ->
            if (list.isNotEmpty()) {
                val calendar = Calendar.getInstance()
                val currentMonth = calendar.get(Calendar.MONTH)
                calendar.time = Date(list.first().createdAt)
                val gamesMonth = calendar.get(Calendar.MONTH)

                if (currentMonth > gamesMonth) {
                    fetchHotGames(
                        gamesCount = gamesCount,
                        dates = dates,
                        metacriticRatings = metacriticRatings
                    )
                }
                calendar.time = Date()
            } else fetchHotGames(
                gamesCount = gamesCount,
                dates = dates,
                metacriticRatings = metacriticRatings
            )
        }.toDomain()

    private suspend fun fetchHotGames(gamesCount: Int, dates: String, metacriticRatings: String) {
        val hotGamesResponse = gamesApi.getGamesList(
            page = 1,
            pageSize = gamesCount,
            dates = dates,
            metacriticRatings = metacriticRatings
        )
        gamesDao.fetchHotGames(hotGamesResponse.toEntity())
    }

    override suspend fun getUpcomingGames(
        dates: String,
        platformId: Int,
        gamesCount: Int
    ): List<GameItem> =
        gamesApi.getGamesList(
            dates = dates,
            pageSize = gamesCount,
            platforms = platformId.toString(),
            page = 1
        ).toDomainShort()

    override suspend fun getBestGames(
        ratings: String,
        platformId: Int,
        gamesCount: Int
    ): List<GameItem> =
        gamesApi.getGamesList(
            metacriticRatings = ratings,
            pageSize = gamesCount,
            page = 1,
            platforms = platformId.toString()
        ).toDomainShort()

    override suspend fun getLatestGames(
        dates: String,
        platformId: Int,
        gamesCount: Int
    ): List<GameItem> =
        gamesApi.getGamesList(
            dates = dates,
            pageSize = gamesCount,
            page = 1,
            platforms = platformId.toString()
        ).toDomainShort()

    override suspend fun getGamesByDate(date: String): List<GameItem> =
        gamesApi.getGamesList(dates = date).toDomainShort()

}
