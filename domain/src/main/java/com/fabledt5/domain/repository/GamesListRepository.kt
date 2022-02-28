package com.fabledt5.domain.repository

import com.fabledt5.domain.model.GameItem

interface GamesListRepository {

    suspend fun getHotGames(gamesCount: Int): List<GameItem>

    suspend fun getMonthlyGames(dates: String, gamesCount: Int): List<GameItem>

    suspend fun getBestGames(ratings: String, gamesCount: Int): List<GameItem>

    suspend fun getNewGames(dates: String, gamesCount: Int): List<GameItem>

}