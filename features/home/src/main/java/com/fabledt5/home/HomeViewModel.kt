package com.fabledt5.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fabledt5.domain.model.GameItem
import com.fabledt5.domain.model.Resource
import com.fabledt5.domain.use_case.home.GetHotGames
import com.fabledt5.navigation.NavigationManager
import com.fabledt5.navigation.directions.GameDirection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
    private val getHotGames: GetHotGames
) : ViewModel() {

    private val _hotGamesList = MutableStateFlow<Resource<List<GameItem>>>(Resource.Loading)
    val hotGamesList = _hotGamesList.asStateFlow()

    init {
        loadHotGames()
    }

    fun openGameScreen(gameId: Int) =
        navigationManager.navigate(GameDirection.game(gameId = gameId))

    private fun loadHotGames() = viewModelScope.launch(Dispatchers.IO) {
        val hotGames = getHotGames()
        if (hotGames.isEmpty()) _hotGamesList.value = Resource.Error(message = "Empty list")
        else _hotGamesList.value = Resource.Success(data = hotGames)
    }

}