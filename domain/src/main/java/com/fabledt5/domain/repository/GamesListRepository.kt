package com.fabledt5.domain.repository

import com.fabledt5.domain.model.items.GameItem
import kotlinx.coroutines.flow.Flow

interface GamesListRepository {

    fun getHotGames(
        gamesCount: Int,
        dates: String,
        metacriticRatings: String
    ): Flow<List<GameItem>>

    suspend fun getUpcomingGames(dates: String, platformId: Int, gamesCount: Int): List<GameItem>

    suspend fun getBestGames(ratings: String, platformId: Int, gamesCount: Int): List<GameItem>

    suspend fun getLatestGames(dates: String, platformId: Int, gamesCount: Int): List<GameItem>

    suspend fun getGamesByDate(date: String): List<GameItem>

}