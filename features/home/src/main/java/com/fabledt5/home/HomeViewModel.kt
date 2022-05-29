package com.fabledt5.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fabledt5.domain.model.GameItem
import com.fabledt5.domain.model.PlatformItem
import com.fabledt5.domain.model.Resource
import com.fabledt5.domain.use_case.home.HomeCases
import com.fabledt5.navigation.NavigationManager
import com.fabledt5.navigation.directions.GameDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
    private val homeCases: HomeCases
) : ViewModel() {

    private val _favoritePlatform = MutableStateFlow<Resource<PlatformItem>>(Resource.Idle)
    val favoritePlatform = _favoritePlatform.asStateFlow()

    private val _hotGamesList = MutableStateFlow<Resource<List<GameItem>>>(Resource.Loading)
    val hotGamesList = _hotGamesList.asStateFlow()

    private val _platformsList = MutableStateFlow<Resource<List<PlatformItem>>>(Resource.Idle)
    val platformsList = _platformsList.asStateFlow()

    private val _upcomingGames = MutableStateFlow<Resource<List<GameItem>>>(Resource.Loading)
    val upcomingGames = _upcomingGames.asStateFlow()

    private val _bestGames = MutableStateFlow<Resource<List<GameItem>>>(Resource.Loading)
    val bestGames = _bestGames.asStateFlow()

    private val _newGames = MutableStateFlow<Resource<List<GameItem>>>(Resource.Loading)
    val newGames = _newGames.asStateFlow()

    init {
        loadHotGamesList()
        loadPlatformsList()
        loadFavoritePlatform()
    }

    private fun loadHotGamesList() = viewModelScope.launch(Dispatchers.IO) {
        val hotGamesResult = homeCases.getHotGames()
        _hotGamesList.value = if (hotGamesResult.isEmpty()) Resource.Error()
        else Resource.Success(data = hotGamesResult)
    }

    private fun loadPlatformsList() = homeCases.getPlatformsList()
        .onEach { result ->
            _platformsList.value = result
            if (result is Resource.Error) Timber.e(result.exception)
        }.launchIn(viewModelScope)

    private fun loadFavoritePlatform() = homeCases.getFavoritePlatform()
        .onEach { result ->
            _favoritePlatform.value = result
            when (result) {
                is Resource.Error -> Timber.e(result.exception)
                is Resource.Success -> loadGamesLists(result.data.platformId)
                else -> Unit
            }
        }.launchIn(viewModelScope)

    fun changeFavoritePlatformPlatform(platformId: Int) = viewModelScope.launch(Dispatchers.IO) {
        homeCases.setFavoritePlatform(platformId = platformId)
    }

    private fun loadGamesLists(gamesPlatformId: Int) = viewModelScope.launch(Dispatchers.IO) {
        _upcomingGames.value = Resource.Loading
        _bestGames.value = Resource.Loading
        _newGames.value = Resource.Loading

        val upcomingGames = async { homeCases.getUpcomingGames(gamesPlatformId) }
        val upcomingGamesResult = upcomingGames.await()

        val bestGames = async { homeCases.getBestGames(gamesPlatformId) }
        val bestGamesResult = bestGames.await()

        val newGames = async { homeCases.getNewGames(gamesPlatformId) }
        val newGamesResult = newGames.await()

        _upcomingGames.value =
            if (upcomingGamesResult.isEmpty()) Resource.Error()
            else Resource.Success(data = upcomingGamesResult)

        _bestGames.value =
            if (bestGamesResult.isEmpty()) Resource.Error()
            else Resource.Success(data = bestGamesResult)

        _newGames.value =
            if (newGamesResult.isEmpty()) Resource.Error()
            else Resource.Success(data = newGamesResult)
    }

    fun openGameScreen(gameId: Int) =
        navigationManager.navigate(GameDirections.game(gameId = gameId))

}