package com.fabledt5.domain.use_case.home

import com.fabledt5.domain.model.GameItem
import com.fabledt5.domain.repository.GamesListRepository
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class GetUpcomingGames @Inject constructor(
    private val gamesListRepository: GamesListRepository
) {

    suspend operator fun invoke(platformId: Int): List<GameItem> {
        val cal = Calendar.getInstance()
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        val startDate = formatter.format(cal.time)
        cal.add(Calendar.DATE, 31)

        val endDate = formatter.format(cal.time)

        return gamesListRepository.getMonthlyGames(
            dates = "$startDate,$endDate",
            platformId = platformId,
            gamesCount = 8
        )
    }

}