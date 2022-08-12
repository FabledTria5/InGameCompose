package com.fabledt5.domain.use_case.home

import com.fabledt5.domain.model.Resource
import com.fabledt5.domain.model.items.GameItem
import com.fabledt5.domain.repository.GamesListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class GetHotGames @Inject constructor(private val gamesListRepository: GamesListRepository) {

    operator fun invoke(): Flow<Resource<List<GameItem>>> {
        val date = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

        val startDate = date.minusMonths(6).format(formatter)
        val endDate = date.format(formatter)

        return gamesListRepository.getHotGames(
            gamesCount = 6,
            dates = "$startDate,$endDate",
            metacriticRatings = "80,100"
        ).map { list ->
            Resource.Success(data = list)
        }
    }

}