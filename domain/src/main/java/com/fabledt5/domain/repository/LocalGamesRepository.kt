package com.fabledt5.domain.repository

import com.fabledt5.domain.model.GameType
import com.fabledt5.domain.model.items.GameItem
import kotlinx.coroutines.flow.Flow

interface LocalGamesRepository {

    fun readHotGames(): Flow<List<GameItem>>

    fun readSavedGames(gameType: GameType): Flow<List<GameItem>>

    suspend fun insertGame(gameData: Pair<GameItem, GameType>)

    suspend fun deleteGame(gameId: Int, gameType: GameType)

}