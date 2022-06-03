package com.fabledt5.catalogue

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.fabledt5.domain.model.Resource
import com.fabledt5.domain.model.items.DeveloperItem
import com.fabledt5.domain.model.items.GenreItem
import com.fabledt5.domain.model.items.PlatformItem
import com.fabledt5.domain.use_case.search.SearchGames
import com.fabledt5.domain.use_case.search.filters.FiltersCases
import com.fabledt5.navigation.NavigationManager
import com.fabledt5.navigation.directions.GameDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject

@ExperimentalCoroutinesApi
@FlowPreview
@HiltViewModel
class CatalogueViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
    private val filtersCases: FiltersCases,
    private val searchGames: SearchGames
) : ViewModel() {

    private val _developersList = MutableStateFlow<Resource<List<DeveloperItem>>>(Resource.Loading)
    val developersList = _developersList.asStateFlow()

    private val _selectedDevelopers = mutableStateListOf<String>()
    val selectedDevelopers: List<String> = _selectedDevelopers

    private val _genresList = MutableStateFlow<Resource<List<GenreItem>>>(Resource.Loading)
    val genresList = _genresList.asStateFlow()

    private val _selectedGenres = mutableStateListOf<Int>()
    val selectedGenres: List<Int> = _selectedGenres

    private val _platformsList = MutableStateFlow<Resource<List<PlatformItem>>>(Resource.Loading)
    val platformsList = _platformsList.asStateFlow()

    private val _selectedPlatforms = mutableStateListOf<Int>()
    val selectedPlatforms: List<Int> = _selectedPlatforms

    val searchQuery = MutableStateFlow(value = "")

    val searchResults = searchQuery
        .debounce(timeoutMillis = 1000)
        .flatMapLatest { query ->
            searchGames(
                searchQuery = query,
                platforms = selectedPlatforms,
                genres = selectedGenres,
                developers = selectedDevelopers
            ).cachedIn(viewModelScope)
        }
        .stateIn(scope = viewModelScope, started = SharingStarted.Lazily, PagingData.empty())

    init {
        loadDevelopersFilter()
        loadGenresFilter()
        loadPlatformsFilter()
    }

    private fun loadDevelopersFilter() = filtersCases.getDevelopersFilters()
        .onEach { result ->
            _developersList.value = result
            if (result is Resource.Error) Timber.e(result.exception)
        }
        .launchIn(viewModelScope)

    private fun loadGenresFilter() = filtersCases.getGenresFilters()
        .onEach { result ->
            _genresList.value = result
            if (result is Resource.Error) Timber.e(result.exception)
        }
        .launchIn(viewModelScope)

    private fun loadPlatformsFilter() = filtersCases.getPlatformsFilters()
        .onEach { result ->
            _platformsList.value = result
            if (result is Resource.Error) Timber.e(result.exception)
        }
        .launchIn(viewModelScope)

    fun onGameClicked(gameId: Int) = navigationManager.navigate(GameDirections.game(gameId))

    fun onGamesLoadingError(loadState: LoadState) {
        Timber.e((loadState as? LoadState.Error)?.error)
    }

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