package com.fabledt5.domain.use_case.game

import com.fabledt5.domain.model.Resource
import com.fabledt5.domain.repository.GameRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetGameReviews @Inject constructor(
    private val gameRepository: GameRepository
) {

    operator fun invoke(gameUrl: String) = flow {
        emit(Resource.Loading)
        emit(gameRepository.getGameReviews(gameUrl = gameUrl))
    }

}