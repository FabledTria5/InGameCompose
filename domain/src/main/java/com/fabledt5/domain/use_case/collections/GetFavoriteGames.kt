package com.fabledt5.domain.use_case.collections

import com.fabledt5.domain.model.GameType
import com.fabledt5.domain.repository.LocalGamesRepository
import javax.inject.Inject

class GetFavoriteGames @Inject constructor(
    private val localGamesRepository: LocalGamesRepository
) {

    operator fun invoke() = localGamesRepository.readSavedGames(GameType.FAVORITE)

}