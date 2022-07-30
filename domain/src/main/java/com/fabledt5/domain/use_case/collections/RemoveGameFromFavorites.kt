package com.fabledt5.domain.use_case.collections

import com.fabledt5.domain.model.GameType
import com.fabledt5.domain.repository.firebase.FireStoreRepository
import javax.inject.Inject

class RemoveGameFromFavorites @Inject constructor(
    private val fireStoreRepository: FireStoreRepository
) {

    suspend operator fun invoke(gameId: Int) =
        fireStoreRepository.removeGameFromCollection(gameId = gameId, gameType = GameType.FAVORITE)

}