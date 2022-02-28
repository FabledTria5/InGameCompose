package com.fabledt5.domain.use_case.home

import com.fabledt5.domain.model.GameItem
import com.fabledt5.domain.repository.GamesListRepository
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class GetNewGames @Inject constructor(
    private val gamesListRepository: GamesListRepository
) {

    suspend operator fun invoke(): List<GameItem> {
        val cal = Calendar.getInstance()
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        cal.add(Calendar.DATE, -31)
        val startDate = formatter.format(cal.time)
        cal.time = Date()
        val endDate = formatter.format(cal.time)

        return gamesListRepository.getNewGames(dates = "$startDate,$endDate", gamesCount = 8)
    }

}