package com.fabledt5.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.fabledt5.domain.model.GameItem
import com.fabledt5.domain.model.Resource
import com.fabledt5.domain.use_case.game.GetGameDetails
import dagger.Module
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class GameViewModel @AssistedInject constructor(
    @Assisted private val gameId: Int,
    private val getGameDetails: GetGameDetails
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

    init {
        loadGameData()
    }

    private fun loadGameData() = getGameDetails(gameId = gameId).onEach { result ->
        _gameData.value = result
    }.launchIn(viewModelScope)

}