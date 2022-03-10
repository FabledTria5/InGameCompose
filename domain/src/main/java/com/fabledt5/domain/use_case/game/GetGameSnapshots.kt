package com.fabledt5.domain.use_case.game

import com.fabledt5.domain.repository.GameRepository
import javax.inject.Inject

class GetGameSnapshots @Inject constructor(
    private val gameRepository: GameRepository
) {

    operator fun invoke(gameId: Int) = gameRepository.getGameSnapShots(gameId = gameId)

}