package com.fabledt5.domain.use_case.search.filters

import com.fabledt5.domain.repository.FiltersRepository
import javax.inject.Inject

class GetDevelopersFilters @Inject constructor(private val filtersRepository: FiltersRepository) {

    operator fun invoke() = filtersRepository.getGameDevelopers()

}