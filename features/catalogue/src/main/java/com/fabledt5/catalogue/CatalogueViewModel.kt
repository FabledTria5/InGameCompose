package com.fabledt5.catalogue

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.fabledt5.domain.model.items.DeveloperItem
import com.fabledt5.domain.model.items.GenreItem
import com.fabledt5.domain.model.items.PlatformItem
import com.fabledt5.domain.use_case.search.SearchGames
import com.fabledt5.domain.use_case.search.filters.FiltersCases
import com.fabledt5.navigation.NavigationManager
import com.fabledt5.navigation.directions.GameDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class CatalogueViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
    private val filtersCases: FiltersCases,
    private val searchGames: SearchGames
) : ViewModel() {

    private val _developersList = mutableStateListOf<DeveloperItem>()
    val developersList: List<DeveloperItem> = _developersList

    private val _selectedDevelopers = mutableStateListOf<String>()
    val selectedDevelopers: List<String> = _selectedDevelopers

    private val _genresList = mutableStateListOf<GenreItem>()
    val genresList: List<GenreItem> = _genresList

    private val _selectedGenres = mutableStateListOf<Int>()
    val selectedGenres: List<Int> = _selectedGenres

    private val _platformsList = mutableStateListOf<PlatformItem>()
    val platformsList: List<PlatformItem> = _platformsList

    private val _selectedPlatforms = mutableStateListOf<Int>()
    val selectedPlatforms: List<Int> = _selectedPlatforms

    val searchQuery = MutableStateFlow(value = "")

    val searchResults = searchQuery
        .flatMapLatest { query ->
            searchGames(
                searchQuery = query,
                platforms = selectedPlatforms,
                genres = selectedGenres,
                developers = selectedDevelopers
            )
        }
        .cachedIn(viewModelScope)
        .stateIn(scope = viewModelScope, started = SharingStarted.Lazily, PagingData.empty())

    init {
        loadDevelopersFilter()
        loadGenresFilter()
        loadPlatformsFilter()
    }

    private fun loadDevelopersFilter() = filtersCases.getDevelopersFilters()
        .flowOn(Dispatchers.IO)
        .onEach { result ->
            _developersList.addAll(result)
        }
        .launchIn(viewModelScope)

    private fun loadGenresFilter() = filtersCases.getGenresFilters()
        .flowOn(Dispatchers.IO)
        .onEach { result ->
            _genresList.addAll(result)
        }
        .launchIn(viewModelScope)

    private fun loadPlatformsFilter() = filtersCases.getPlatformsFilters()
        .flowOn(Dispatchers.IO)
        .onEach { result ->
            _platformsList.addAll(result)
        }
        .launchIn(viewModelScope)

    fun onGameClicked(gameId: Int) = navigationManager.navigate(GameDirections.game(gameId))

    fun togglePlatform(platformId: Int) = if (_selectedPlatforms.contains(platformId))
        _selectedPlatforms.remove(platformId)
    else
        _selectedPlatforms.add(platformId)

    fun toggleGenre(genreId: Int) = if (_selectedGenres.contains(genreId))
        _selectedGenres.remove(genreId)
    else
        _selectedGenres.add(genreId)

    fun toggleDeveloper(developerName: String) = if (_selectedDevelopers.contains(developerName))
        _selectedDevelopers.remove(developerName)
    else
        _selectedDevelopers.add(developerName)

}