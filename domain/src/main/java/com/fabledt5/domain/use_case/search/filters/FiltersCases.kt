package com.fabledt5.domain.use_case.search.filters

import javax.inject.Inject

data class FiltersCases @Inject constructor(
    val getDevelopersFilters: GetDevelopersFilters,
    val getGenresFilters: GetGenresFilters,
    val getPlatformsFilters: GetPlatformsFilters
)
