package com.fabledt5.repository

import com.fabledt5.db.dao.PlatformsDao
import com.fabledt5.domain.repository.PreferencesRepository
import com.fabledt5.mapper.toDomain
import com.fabledt5.preferences.AppPreferences
import com.fabledt5.preferences.AppPreferencesImpl
import javax.inject.Inject

class PreferencesRepositoryImpl @Inject constructor(
    private val platformsDao: PlatformsDao,
    private val appPreferences: AppPreferences
) : PreferencesRepository {

    override fun getFavoritePlatform(platformId: Int) =
        platformsDao.getFavoritePlatform(platformId = platformId).toDomain()

    override fun getFavoritePlatformId() = appPreferences.readFavoritePlatform

    override suspend fun saveFavoritePlatform(platformId: Int) =
        appPreferences.persistFavoritePlatform(platformId = platformId)

}