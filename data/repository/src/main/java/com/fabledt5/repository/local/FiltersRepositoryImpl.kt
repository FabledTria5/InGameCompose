package com.fabledt5.repository.local

import com.fabledt5.db.dao.FiltersDao
import com.fabledt5.domain.repository.FiltersRepository
import com.fabledt5.mapper.toDomain
import javax.inject.Inject

class FiltersRepositoryImpl @Inject constructor(
    private val filtersDao: FiltersDao
) : FiltersRepository {

    override fun getGameGenres() = filtersDao.getGenres().toDomain()

    override fun getGameDevelopers() = filtersDao.getDevelopers().toDomain()

    override fun getGamePlatforms() = filtersDao.getPlatforms().toDomain()

    override fun getFavoritePlatform() = filtersDao.getFavoritePlatform().toDomain()

    override suspend fun setFavoritePlatform(platformId: Int) =
        filtersDao.setFavoritePlatform(platformId = platformId)
}