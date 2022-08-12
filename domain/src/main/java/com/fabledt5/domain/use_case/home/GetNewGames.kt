package com.fabledt5.domain.use_case.home

import com.fabledt5.domain.model.Resource
import com.fabledt5.domain.model.items.GameItem
import com.fabledt5.domain.repository.GamesListRepository
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class GetNewGames @Inject constructor(
    private val gamesListRepository: GamesListRepository
) {

    suspend operator fun invoke(platformId: Int): Resource<List<GameItem>> {
        val date = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

        val startDate = date.minusDays(31).format(formatter)
        val endDate = date.format(formatter)

        return gamesListRepository.getLatestGames(
            dates = "$startDate,$endDate",
            platformId = platformId,
            gamesCount = 8
        )
    }

}