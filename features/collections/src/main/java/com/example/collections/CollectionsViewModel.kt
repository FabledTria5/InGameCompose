package com.example.collections

import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fabledt5.domain.model.DefaultErrors
import com.fabledt5.domain.model.Resource
import com.fabledt5.domain.model.items.GameItem
import com.fabledt5.domain.use_case.collections.CollectionsCases
import com.fabledt5.navigation.NavigationManager
import com.fabledt5.navigation.directions.GameDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class CollectionsViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
    private val collectionsCases: CollectionsCases
) : ViewModel() {

    private val _calendarGamesCache = mutableMapOf<LocalDate, Resource<List<GameItem>>>()
    val calendarGamesMap = mutableStateMapOf<LocalDate, Resource<List<GameItem>>>()

    private val _favoriteGames = MutableStateFlow<Resource<List<GameItem>>>(Resource.Loading)
    val favoriteGames = _favoriteGames.asStateFlow()

    private val _playedGames = MutableStateFlow<Resource<List<GameItem>>>(Resource.Loading)
    val playedGames = _playedGames.asStateFlow()

    init {
        dateSelected(LocalDate.now())
        getFavoriteGames()
        getPlayedGames()
    }

    private fun getFavoriteGames() = collectionsCases.getFavoriteGames()
        .onEach { result ->
            _favoriteGames.value = if (result.isEmpty()) Resource.Idle
            else Resource.Success(result)
        }
        .catch { exception ->
            Timber.e(exception)
            _favoriteGames.value = Resource.Error(DefaultErrors.UnknownError(""))
        }
        .flowOn(Dispatchers.IO)
        .launchIn(viewModelScope)

    private fun getPlayedGames() = collectionsCases.getPlayedGames()
        .onEach { result ->
            _playedGames.value = if (result.isEmpty()) Resource.Idle
            else Resource.Success(result)
        }
        .catch { exception ->
            Timber.e(exception)
            _favoriteGames.value = Resource.Error(DefaultErrors.UnknownError(""))
        }
        .flowOn(Dispatchers.IO)
        .launchIn(viewModelScope)

    fun addGameToFavorites(gameItem: GameItem) = viewModelScope.launch(Dispatchers.IO) {
        collectionsCases.markAsFavorite(gameItem)
    }

    fun addGameToPlayed(gameItem: GameItem) = viewModelScope.launch(Dispatchers.IO) {
        collectionsCases.markAsPlayed(gameItem)
    }

    fun dateSelected(localDate: LocalDate) {
        if (calendarGamesMap.containsKey(localDate)) {
            calendarGamesMap.remove(localDate)
            return
        }
        val cachedData = _calendarGamesCache[localDate]
        if (cachedData != null) {
            calendarGamesMap[localDate] = cachedData
            return
        }
        collectionsCases.getGamesByDate(localDate)
            .flowOn(Dispatchers.IO)
            .onEach { resource ->
                calendarGamesMap[localDate] = resource
                _calendarGamesCache[localDate] = resource
            }
            .launchIn(viewModelScope)
    }

    fun gameClicked(gameId: Int) = navigationManager.navigate(GameDirections.game(gameId))

}