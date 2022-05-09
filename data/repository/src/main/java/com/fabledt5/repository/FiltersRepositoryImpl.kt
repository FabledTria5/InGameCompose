package com.fabledt5.repository

import com.fabledt5.db.dao.FiltersDao
import com.fabledt5.domain.repository.FiltersRepository
import com.fabledt5.mapper.toDomain
import javax.inject.Inject

class FiltersRepositoryImpl @Inject constructor(private val filtersDao: FiltersDao) :
    FiltersRepository {

    override fun getDevelopersList() = filtersDao.getDevelopers().toDomain()

}