package com.fabledt5.domain.repository.firebase

import com.fabledt5.domain.model.GameType
import com.fabledt5.domain.model.Resource
import com.fabledt5.domain.model.items.GameItem
import kotlinx.coroutines.flow.Flow

interface FireStoreRepository {

    fun updateFavoriteGames(): Flow<Resource<Pair<GameItem, GameType>>>

    fun updatePlayedGames(): Flow<Resource<Pair<GameItem, GameType>>>

    suspend fun createUser(
        userEmail: String,
        userNickname: String
    ): Resource<Boolean>

    suspend fun addGameToCollection(
        gameItem: GameItem,
        gameType: GameType
    )

    suspend fun removeGameFromCollection(gameId: Int, gameType: GameType)

}