package com.fabledt5.domain.use_case.home

import com.fabledt5.domain.repository.FiltersRepository
import javax.inject.Inject

class SetFavoritePlatform @Inject constructor(
    private val filtersRepository: FiltersRepository
) {

    suspend operator fun invoke(platformId: Int) =
        filtersRepository.setFavoritePlatform(platformId = platformId)

}