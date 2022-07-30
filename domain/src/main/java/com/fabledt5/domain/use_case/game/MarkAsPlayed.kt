package com.fabledt5.domain.use_case.game

import com.fabledt5.domain.model.GameType
import com.fabledt5.domain.model.items.GameItem
import com.fabledt5.domain.repository.firebase.FireStoreRepository
import javax.inject.Inject

class MarkAsPlayed @Inject constructor(
    private val fireStoreRepository: FireStoreRepository
) {

    suspend operator fun invoke(gameItem: GameItem) =
        fireStoreRepository.addGameToCollection(gameItem, GameType.PLAYED)

}