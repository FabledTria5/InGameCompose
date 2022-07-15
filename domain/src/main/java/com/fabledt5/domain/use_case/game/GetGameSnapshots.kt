package com.fabledt5.domain.use_case.game

import com.fabledt5.domain.model.Resource
import com.fabledt5.domain.repository.GameRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetGameSnapshots @Inject constructor(
    private val gameRepository: GameRepository
) {

    operator fun invoke(gameId: Int) = flow {
        emit(Resource.Loading)
        emit(gameRepository.getGameSnapShots(gameId = gameId))
    }

}