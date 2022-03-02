package com.fabledt5.domain.repository

import com.fabledt5.domain.model.GameItem
import com.fabledt5.domain.model.PlatformItem

interface GamesListRepository {

    suspend fun getHotGames(gamesCount: Int): List<GameItem>

    suspend fun getMonthlyGames(dates: String, platformId: Int, gamesCount: Int): List<GameItem>

    suspend fun getBestGames(ratings: String, platformId: Int, gamesCount: Int): List<GameItem>

    suspend fun getNewGames(dates: String, platformId: Int, gamesCount: Int): List<GameItem>

    suspend fun getPlatformsList(): List<PlatformItem>

}