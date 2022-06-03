package com.fabledt5.domain.use_case.home

import com.fabledt5.domain.repository.GamesListRepository
import javax.inject.Inject

class GetBestGames @Inject constructor(
    private val gamesListRepository: GamesListRepository
) {

    suspend operator fun invoke(platformId: Int) =
        gamesListRepository.getBestGames(
            ratings = "90,100",
            platformId = platformId,
            gamesCount = 8
        )

}