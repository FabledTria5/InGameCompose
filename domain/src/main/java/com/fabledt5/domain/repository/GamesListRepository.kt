package com.fabledt5.domain.repository

import com.fabledt5.domain.model.GameItem

interface GamesListRepository {

    suspend fun getHotGames(gamesCount: Int): List<GameItem>

}