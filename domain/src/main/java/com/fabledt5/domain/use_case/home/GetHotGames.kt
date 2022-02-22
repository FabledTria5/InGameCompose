package com.fabledt5.domain.use_case.home

import com.fabledt5.domain.repository.GamesListRepository
import javax.inject.Inject

class GetHotGames @Inject constructor(private val gamesListRepository: GamesListRepository) {

    suspend operator fun invoke() = gamesListRepository.getHotGames(gamesCount = 6)

}