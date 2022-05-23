package com.fabledt5.domain.use_case.home

import com.fabledt5.domain.repository.FiltersRepository
import javax.inject.Inject

class GetFavoritePlatform @Inject constructor(
    private val filtersRepository: FiltersRepository
) {

    operator fun invoke() = filtersRepository.getFavoritePlatform()

}