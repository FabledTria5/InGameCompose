package com.fabledt5.domain.repository

import com.fabledt5.domain.model.Resource
import com.fabledt5.domain.model.items.GameItem
import com.fabledt5.domain.model.items.RatingItem

interface GameRepository {

    suspend fun getGameDetails(gameId: Int): Resource<GameItem>

    suspend fun getGameSnapShots(gameId: Int): Resource<List<String>>

    fun getGameReviews(gameUrl: String): Resource<RatingItem>

}