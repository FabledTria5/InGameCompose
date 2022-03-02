package com.fabledt5.domain.use_case.home

import com.fabledt5.domain.repository.PreferencesRepository
import javax.inject.Inject

class GetFavoritePlatformId @Inject constructor(
    private val preferencesRepository: PreferencesRepository
) {

    operator fun invoke() = preferencesRepository.getFavoritePlatformId()

}