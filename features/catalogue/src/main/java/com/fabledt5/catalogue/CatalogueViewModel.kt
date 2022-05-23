package com.fabledt5.catalogue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fabledt5.domain.model.DeveloperItem
import com.fabledt5.domain.model.GameGenre
import com.fabledt5.domain.model.PlatformItem
import com.fabledt5.domain.model.Resource
import com.fabledt5.domain.use_case.search.filters.FiltersCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CatalogueViewModel @Inject constructor(
    private val filtersCases: FiltersCases
) : ViewModel() {

    private val _developersList = MutableStateFlow<Resource<List<DeveloperItem>>>(Resource.Loading)
    val developersList = _developersList.asStateFlow()

    private val _genresList = MutableStateFlow<Resource<List<GameGenre>>>(Resource.Loading)
    val genresList = _genresList.asStateFlow()

    private val _platformsList = MutableStateFlow<Resource<List<PlatformItem>>>(Resource.Loading)
    val platformsList = _platformsList.asStateFlow()

    init {
        loadDevelopersFilter()
        loadGenresFilter()
        loadPlatformsFilter()
    }

    private fun loadDevelopersFilter() = filtersCases.getDevelopersFilters()
        .flowOn(Dispatchers.IO)
        .onEach { result ->
            _developersList.value = result
            if (result is Resource.Error) Timber.e(result.exception)
        }.launchIn(viewModelScope)

    private fun loadGenresFilter() = filtersCases.getGenresFilters()
        .flowOn(Dispatchers.IO)
        .onEach { result ->
            _genresList.value = result
            if (result is Resource.Error) Timber.e(result.exception)
        }.launchIn(viewModelScope)

    private fun loadPlatformsFilter() = filtersCases.getPlatformsFilters()
        .flowOn(Dispatchers.IO)
        .onEach { result ->
            _platformsList.value = result
            if (result is Resource.Error) Timber.e(result.exception)
        }.launchIn(viewModelScope)

}