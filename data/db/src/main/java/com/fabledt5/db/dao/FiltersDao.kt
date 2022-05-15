package com.fabledt5.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.fabledt5.db.entities.DeveloperEntity
import com.fabledt5.db.entities.GenreEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FiltersDao {

    @Query(value = "SELECT * FROM developers_table")
    fun getDevelopers(): Flow<List<DeveloperEntity>>

    @Query(value = "SELECT * FROM genres_table")
    fun getGenres(): Flow<List<GenreEntity>>

}