package com.fabledt5.domain.use_case.collections

import com.fabledt5.domain.model.Resource
import com.fabledt5.domain.repository.LocalGamesRepository
import com.fabledt5.domain.repository.firebase.FireStoreRepository
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class UpdateSavedGames @Inject constructor(
    private val fireStoreRepository: FireStoreRepository,
    private val localRepository: LocalGamesRepository
) {

    operator fun invoke() = merge(
        fireStoreRepository.updateFavoriteGames(),
        fireStoreRepository.updatePlayedGames()
    )
        .onEach { result ->
            when (result) {
                is Resource.Success -> localRepository.insertGame(result.data)
                is Resource.Error -> result.data?.let {
                    localRepository.deleteGame(
                        it.first.gameId,
                        it.second
                    )
                }
                else -> Unit
            }
        }

}