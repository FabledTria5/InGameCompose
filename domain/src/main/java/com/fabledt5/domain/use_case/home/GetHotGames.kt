package com.fabledt5.domain.use_case.home

import com.fabledt5.domain.model.Resource
import com.fabledt5.domain.model.items.GameItem
import com.fabledt5.domain.repository.GamesListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class GetHotGames @Inject constructor(private val gamesListRepository: GamesListRepository) {

    operator fun invoke(): Flow<Resource<List<GameItem>>> {
        val cal = Calendar.getInstance()
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        cal.add(Calendar.MONTH, -6)
        val startDate = formatter.format(cal.time)
        cal.time = Date()
        val endDate = formatter.format(cal.time)

        return gamesListRepository.getHotGames(
            gamesCount = 6,
            dates = "$startDate,$endDate",
            metacriticRatings = "80,100"
        ).map { list ->
            if (list.isNotEmpty()) Resource.Success(data = list)
            else Resource.Error(exception = Throwable("Empty list"))
        }
    }

}