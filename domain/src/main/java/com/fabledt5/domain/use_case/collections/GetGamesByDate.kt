package com.fabledt5.domain.use_case.collections

import com.fabledt5.domain.model.Resource
import com.fabledt5.domain.repository.GamesListRepository
import kotlinx.coroutines.flow.flow
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class GetGamesByDate @Inject constructor(
    private val gamesListRepository: GamesListRepository
) {

    operator fun invoke(date: LocalDate) = flow {
        emit(Resource.Loading)

        val formattedDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        val gamesResult = gamesListRepository.getGamesByDate("$formattedDate,$formattedDate")
        emit(gamesResult)
    }

}