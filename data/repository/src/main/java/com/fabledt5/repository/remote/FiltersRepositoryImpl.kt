package com.fabledt5.repository.remote

import com.fabledt5.db.dao.FiltersDao
import com.fabledt5.domain.repository.FiltersRepository
import com.fabledt5.mapper.toDomain
import com.fabledt5.mapper.toEntity
import com.fabledt5.remote.api.InGameService
import javax.inject.Inject

class FiltersRepositoryImpl @Inject constructor(
    private val inGameService: InGameService,
    private val filtersDao: FiltersDao
) : FiltersRepository {

    override suspend fun fetchDevelopersList() {
        val developersResponse = inGameService.getGamesDevelopers()
        filtersDao.insertDevelopers(developersResponse.toEntity())
    }

    override suspend fun fetchGenresList() {
        val genresResponse = inGameService.getGamesGenres()
        filtersDao.insertGenres(genresResponse.toEntity())
    }

    override suspend fun fetchPlatformsList() {
        val platformsResponse = inGameService.getGamesPlatforms()
        filtersDao.insertPlatforms(platformsResponse.toEntity())
    }

    override fun getGameGenres() = filtersDao.getGenres().toDomain()

    override fun getGameDevelopers() = filtersDao.getDevelopers().toDomain()

    override fun getGamePlatforms() = filtersDao.getPlatforms().toDomain()

    override fun getFavoritePlatform() = filtersDao.observeFavoritePlatform().toDomain()

    override suspend fun setFavoritePlatform(platformId: Int) =
        filtersDao.setFavoritePlatform(platformId = platformId)
}