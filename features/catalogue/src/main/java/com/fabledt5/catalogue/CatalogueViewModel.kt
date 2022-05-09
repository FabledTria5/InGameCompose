package com.fabledt5.catalogue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fabledt5.domain.model.DeveloperItem
import com.fabledt5.domain.model.Resource
import com.fabledt5.domain.use_case.search.GetDevelopersFilters
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CatalogueViewModel @Inject constructor(private val getDevelopersFilters: GetDevelopersFilters) :
    ViewModel() {

    private val _developersFilters =
        MutableStateFlow<Resource<List<DeveloperItem>>>(Resource.Loading)
    val developersFilters = _developersFilters.asStateFlow()

    init {
        loadDevelopersFilter()
    }

    private fun loadDevelopersFilter() = getDevelopersFilters()
        .onEach { result ->
            _developersFilters.value = result
            if (result is Resource.Error) Timber.e(result.exception)
        }.launchIn(viewModelScope)

}