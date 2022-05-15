package com.fabledt5.catalogue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fabledt5.domain.model.DeveloperItem
import com.fabledt5.domain.model.GameGenre
import com.fabledt5.domain.model.Resource
import com.fabledt5.domain.use_case.search.GetDevelopersFilters
import com.fabledt5.domain.use_case.search.GetGenresFilters
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CatalogueViewModel @Inject constructor(
    private val getDevelopersFilters: GetDevelopersFilters,
    private val getGenresFilters: GetGenresFilters
) : ViewModel() {

    private val _developersList = MutableStateFlow<Resource<List<DeveloperItem>>>(Resource.Loading)
    val developersList = _developersList.asStateFlow()

    private val _genresList = MutableStateFlow<Resource<List<GameGenre>>>(Resource.Loading)
    val genresList = _genresList.asStateFlow()

    init {
        loadDevelopersFilter()
        loadGenresFilter()
    }

    private fun loadDevelopersFilter() = getDevelopersFilters()
        .onEach { result ->
            _developersList.value = result
            if (result is Resource.Error) Timber.e(result.exception)
        }.launchIn(viewModelScope)

    private fun loadGenresFilter() = getGenresFilters()
        .onEach { result ->
            _genresList.value = result
            if (result is Resource.Error) Timber.e(result.exception)
        }.launchIn(viewModelScope)

}