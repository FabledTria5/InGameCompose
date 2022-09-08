package com.fabledt5.home

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fabledt5.domain.model.Resource
import com.fabledt5.domain.model.items.GameItem
import com.fabledt5.domain.model.items.PlatformItem
import com.fabledt5.domain.use_case.home.HomeCases
import com.fabledt5.navigation.NavigationManager
import com.fabledt5.navigation.directions.GameDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
@Stable
class HomeViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
    private val homeCases: HomeCases
) : ViewModel() {

    private val _favoritePlatform = MutableStateFlow(PlatformItem())
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

    private fun loadHotGamesList() = homeCases.getHotGames()
        .onEach { result ->
            _hotGamesList.value = result
            if (result is Resource.Error) Timber.e(result.error)
        }
        .catch { exception ->
            Timber.e(exception)
        }
        .flowOn(Dispatchers.IO)
        .launchIn(viewModelScope)

    private fun loadPlatformsList() = homeCases.getPlatformsList()
        .onEach { result ->
            _platformsList.value = result
        }
        .catch { exception ->
            Timber.e(exception)
        }
        .flowOn(Dispatchers.IO)
        .launchIn(viewModelScope)

    private fun loadFavoritePlatform() = homeCases.getFavoritePlatform()
        .onEach { result ->
            _favoritePlatform.value = result
            loadGamesLists(gamesPlatformId = result.platformId)
        }
        .catch { exception ->
            Timber.e(exception)
        }
        .flowOn(Dispatchers.IO)
        .launchIn(viewModelScope)

    fun changeFavoritePlatform(platformId: Int) = viewModelScope.launch(Dispatchers.IO) {
        homeCases.setFavoritePlatform(platformId = platformId)
    }

    private fun loadGamesLists(gamesPlatformId: Int) = viewModelScope.launch(Dispatchers.IO) {
        launch {
            _upcomingGames.value = Resource.Loading
            _upcomingGames.value = homeCases.getUpcomingGames(gamesPlatformId)
        }

        launch {
            _bestGames.value = Resource.Loading
            _bestGames.value = homeCases.getBestGames(gamesPlatformId)
        }

        launch {
            _newGames.value = Resource.Loading
            _newGames.value = homeCases.getNewGames(gamesPlatformId)
        }
    }

    fun openGameScreen(gameId: Int) =
        navigationManager.navigate(GameDirections.game(gameId = gameId))

}