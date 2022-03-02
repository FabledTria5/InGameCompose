package com.fabledt5.domain.use_case.home

import com.fabledt5.domain.repository.PreferencesRepository
import javax.inject.Inject

class SetFavoritePlatform @Inject constructor(
    private val preferencesRepository: PreferencesRepository
) {

    suspend operator fun invoke(platformId: Int) =
        preferencesRepository.saveFavoritePlatform(platformId = platformId)

}