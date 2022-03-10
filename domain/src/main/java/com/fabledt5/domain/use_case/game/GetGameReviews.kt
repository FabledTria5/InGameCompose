package com.fabledt5.domain.use_case.game

import com.fabledt5.domain.repository.GameRepository
import javax.inject.Inject

class GetGameReviews @Inject constructor(
    private val gameRepository: GameRepository
) {

    operator fun invoke(gameUrl: String) = gameRepository.getGameReviews(gameUrl = gameUrl)

}