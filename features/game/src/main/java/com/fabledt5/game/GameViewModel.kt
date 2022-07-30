package com.fabledt5.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.fabledt5.domain.model.DefaultErrors
import com.fabledt5.domain.model.Resource
import com.fabledt5.domain.model.items.GameItem
import com.fabledt5.domain.model.items.RatingItem
import com.fabledt5.domain.use_case.game.GameCases
import com.fabledt5.navigation.NavigationManager
import com.fabledt5.navigation.directions.GameDirections
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber

class GameViewModel @AssistedInject constructor(
    @Assisted private val gameId: Int,
    private val navigationManager: NavigationManager,
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

    private val _gameReviews = MutableStateFlow<Resource<RatingItem>>(Resource.Loading)
    val gameReviews = _gameReviews.asStateFlow()

    init {
        loadGameData()
    }

    private fun loadGameData() = gameCases.getGameDetails(gameId = gameId)
        .onEach { result ->
            _gameData.value = result
            if (result is Resource.Success) {
                loadGameSnapshots()
                val gameUrl = result.data.gameReviewsUrl
                Timber.d(gameUrl)
                loadGameReviews(gameUrl)
            }
        }
        .catch { exception ->
            Timber.e(exception)
            _gameData.value = Resource.Error(DefaultErrors.UnknownError(errorMessage = ""))
        }
        .flowOn(Dispatchers.IO)
        .launchIn(viewModelScope)

    private fun loadGameSnapshots() = gameCases.getGameSnapshots(gameId = gameId)
        .onEach { result ->
            _gameSnapshots.value = result
        }
        .catch { exception ->
            Timber.e(exception)
            _gameSnapshots.value = Resource.Success(listOf())
        }
        .flowOn(Dispatchers.IO)
        .launchIn(viewModelScope)

    private fun loadGameReviews(gameReviewsUrl: String?) {
        if (!gameReviewsUrl.isNullOrEmpty()) {
            gameCases.getGameReviews(gameReviewsUrl).onEach { result ->
                _gameReviews.value = result
            }
                .catch { exception ->
                    Timber.e(exception)
                    _gameReviews.value = Resource.Success(RatingItem())
                }
                .flowOn(Dispatchers.IO)
                .launchIn(viewModelScope)
        } else _gameReviews.value = Resource.Success(RatingItem())
    }

    fun openReviewsScreen() = navigationManager.navigate(GameDirections.reviews(gameId = gameId))

    fun markGameAsPlayed(gameItem: GameItem) = viewModelScope.launch(Dispatchers.IO) {
        gameCases.markAsPlayed(gameItem)
    }

    fun onBackClicked() = navigationManager.navigateBack()

}