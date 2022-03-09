package com.fabledt5.domain.repository

import com.fabledt5.domain.model.GameItem
import com.fabledt5.domain.model.Resource
import kotlinx.coroutines.flow.Flow

interface GameRepository {

    fun getGameDetails(gameId: Int): Flow<Resource<GameItem>>

    fun getGameSnapShots(gameId: Int): Flow<Resource<List<String>>>

    fun getGameReviews(gameUrl: String)

}