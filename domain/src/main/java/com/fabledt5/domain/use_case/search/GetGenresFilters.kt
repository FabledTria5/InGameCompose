package com.fabledt5.domain.use_case.search

import com.fabledt5.domain.model.Resource
import com.fabledt5.domain.repository.FiltersRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetGenresFilters @Inject constructor(private val filtersRepository: FiltersRepository) {

    operator fun invoke() = filtersRepository.getGenresList().map {
        if (it.isNotEmpty()) Resource.Success(data = it)
        else Resource.Error(exception = Throwable("Empty array"))
    }.catch {
        emit(Resource.Error(exception = it))
    }

}