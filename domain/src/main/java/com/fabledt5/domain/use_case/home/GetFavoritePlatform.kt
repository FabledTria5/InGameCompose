package com.fabledt5.domain.use_case.home

import com.fabledt5.domain.repository.PreferencesRepository
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

class GetFavoritePlatform @Inject constructor(
    private val preferencesRepository: PreferencesRepository
) {

    operator fun invoke(platformId: Int) =
        preferencesRepository.getFavoritePlatform(platformId = platformId)
            .catch { exception ->
                println("${exception.message}\n")
                exception.printStackTrace()
            }

}