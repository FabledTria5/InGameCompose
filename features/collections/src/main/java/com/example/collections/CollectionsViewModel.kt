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
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CollectionsViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
    private val collectionsCases: CollectionsCases
) : ViewModel() {

    companion object {
        private const val CALENDAR_DATES_FORMAT = "dd MMM. yyyy"
    }

    private val _calendarGamesCache = mutableMapOf<String, Resource<List<GameItem>>>()
    val calendarGamesMap = mutableStateMapOf<String, Resource<List<GameItem>>>()

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

    fun removeFromFavorites(gameId: Int) = viewModelScope.launch(Dispatchers.IO) {
        collectionsCases.removeGameFromFavorites(gameId)
    }

    fun dateSelected(localDate: LocalDate) {
        val formattedDate =
            localDate.format(DateTimeFormatter.ofPattern(CALENDAR_DATES_FORMAT, Locale.ENGLISH))
        if (calendarGamesMap.containsKey(formattedDate)) {
            calendarGamesMap.remove(formattedDate)
            return
        }
        val cachedData = _calendarGamesCache[formattedDate]
        if (cachedData != null) {
            calendarGamesMap[formattedDate] = cachedData
            return
        }
        collectionsCases.getGamesByDate(localDate)
            .flowOn(Dispatchers.IO)
            .onEach { resource ->
                calendarGamesMap[formattedDate] = resource
                _calendarGamesCache[formattedDate] = resource
            }
            .launchIn(viewModelScope)
    }

    fun gameClicked(gameId: Int) = navigationManager.navigate(GameDirections.game(gameId))

}