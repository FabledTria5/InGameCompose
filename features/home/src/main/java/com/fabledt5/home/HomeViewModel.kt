package com.fabledt5.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fabledt5.domain.model.GameItem
import com.fabledt5.domain.model.PlatformItem
import com.fabledt5.domain.model.Resource
import com.fabledt5.domain.use_case.home.HomeCases
import com.fabledt5.navigation.NavigationManager
import com.fabledt5.navigation.directions.GameDirection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
    private val homeCases: HomeCases
) : ViewModel() {

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
        loadGamesLists()
    }

    private fun loadPlatformsList() = viewModelScope.launch(Dispatchers.IO) {
        val platformsListResult = homeCases.getPlatformsList()
        _platformsList.value =
            if (platformsListResult.isNotEmpty()) Resource.Success(data = platformsListResult)
            else Resource.Error(message = "Empty list")
    }

    private fun loadHotGamesList() = viewModelScope.launch(Dispatchers.IO) {
        val hotGamesResult = homeCases.getHotGames()
        _hotGamesList.value = if (hotGamesResult.isEmpty()) Resource.Error(message = "Empty list")
        else Resource.Success(data = hotGamesResult)
    }

    private fun loadGamesLists() = viewModelScope.launch(Dispatchers.IO) {
        val upcomingGames = async { homeCases.getUpcomingGames() }
        val upcomingGamesResult = upcomingGames.await()

        val bestGames = async { homeCases.getBestGames() }
        val bestGamesResult = bestGames.await()

        val newGames = async { homeCases.getNewGames() }
        val newGamesResult = newGames.await()

        _upcomingGames.value =
            if (upcomingGamesResult.isEmpty()) Resource.Error(message = "Empty list")
            else Resource.Success(data = upcomingGamesResult)

        _bestGames.value =
            if (bestGamesResult.isEmpty()) Resource.Error(message = "Empty list")
            else Resource.Success(data = bestGamesResult)

        _newGames.value =
            if (newGamesResult.isEmpty()) Resource.Error(message = "Empty list")
            else Resource.Success(data = newGamesResult)
    }

    fun openGameScreen(gameId: Int) =
        navigationManager.navigate(GameDirection.game(gameId = gameId))

}