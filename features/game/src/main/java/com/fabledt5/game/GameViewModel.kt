package com.fabledt5.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.fabledt5.domain.model.GameItem
import com.fabledt5.domain.model.Resource
import com.fabledt5.domain.model.ReviewItem
import com.fabledt5.domain.use_case.game.GameCases
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class GameViewModel @AssistedInject constructor(
    @Assisted private val gameId: Int,
    private val gameCases: GameCases
) : ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(gameId: Int): GameViewModel
    }

    @Suppress("UNCHECKED_CAST")
    companion object {
        fun provideFactory(assistedFactory: Factory, gameId: Int): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return assistedFactory.create(gameId = gameId) as T
                }
            }
    }

    private val _gameData = MutableStateFlow<Resource<GameItem>>(Resource.Loading)
    val gameData = _gameData.asStateFlow()

    private val _gameSnapshots = MutableStateFlow<Resource<List<String>>>(Resource.Loading)
    val gameSnapshots = _gameSnapshots.asStateFlow()

    private val _gameReviews = MutableStateFlow<List<ReviewItem>>(emptyList())
    val gameReviews = _gameReviews.asStateFlow()

    init {
        loadGameData()
        loadGameSnapshots()
        loadGameReviews()
    }

    private fun loadGameData() = gameCases.getGameDetails(gameId = gameId).onEach { result ->
        _gameData.value = result
    }.launchIn(viewModelScope)

    private fun loadGameSnapshots() = gameCases.getGameSnapshots(gameId = gameId).onEach { result ->
        _gameSnapshots.value = result
    }.launchIn(viewModelScope)

    private fun loadGameReviews() {

    }

}