package com.fabledt5.domain.use_case.game

import com.fabledt5.domain.model.Resource
import com.fabledt5.domain.repository.GameRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetGameReviews @Inject constructor(
    private val gameRepository: GameRepository
) {

    operator fun invoke(gameUrl: String) = flow {
        val gameReviews = gameRepository.getGameReviews(gameUrl = gameUrl)
        if (gameReviews.isNotEmpty()) emit(Resource.Success(data = gameReviews))
        else emit(Resource.Error(exception = Throwable("Empty list")))
    }.catch { exception ->
        emit(Resource.Error(exception = exception))
    }

}