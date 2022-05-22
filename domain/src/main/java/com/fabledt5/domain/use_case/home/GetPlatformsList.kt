package com.fabledt5.domain.use_case.home

import com.fabledt5.domain.model.Resource
import com.fabledt5.domain.repository.FiltersRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPlatformsList @Inject constructor(
    private val filtersRepository: FiltersRepository
) {

    operator fun invoke() = filtersRepository.getGamePlatforms().map { list ->
        if (list.isNotEmpty()) Resource.Success(data = list)
        else Resource.Error(exception = Throwable("Empty list"))
    }.catch {
        emit(Resource.Error(exception = it))
    }

}