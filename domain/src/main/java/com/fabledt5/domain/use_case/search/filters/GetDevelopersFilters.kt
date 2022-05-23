package com.fabledt5.domain.use_case.search.filters

import com.fabledt5.domain.model.Resource
import com.fabledt5.domain.repository.FiltersRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetDevelopersFilters @Inject constructor(private val filtersRepository: FiltersRepository) {

    operator fun invoke() = filtersRepository.getGameDevelopers().map { list ->
        if (list.isNotEmpty()) Resource.Success(data = list)
        else {
            filtersRepository.fetchDevelopersList()
            Resource.Loading
        }
    }.catch {
        emit(Resource.Error(exception = it))
    }

}