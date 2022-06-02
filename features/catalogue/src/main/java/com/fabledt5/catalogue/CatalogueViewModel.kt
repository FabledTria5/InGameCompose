package com.fabledt5.catalogue

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fabledt5.domain.model.Resource
import com.fabledt5.domain.model.items.DeveloperItem
import com.fabledt5.domain.model.items.GenreItem
import com.fabledt5.domain.model.items.PlatformItem
import com.fabledt5.domain.use_case.search.filters.FiltersCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CatalogueViewModel @Inject constructor(
    private val filtersCases: FiltersCases
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