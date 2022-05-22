package com.fabledt5.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fabledt5.db.entities.DeveloperEntity
import com.fabledt5.db.entities.GenreEntity
import com.fabledt5.db.entities.PlatformEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FiltersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDevelopers(developersList: List<DeveloperEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGenres(genresList: List<GenreEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlatforms(platformsList: List<PlatformEntity>)

    @Query(value = "SELECT * FROM developers_table")
    fun getDevelopers(): Flow<List<DeveloperEntity>>

    @Query(value = "SELECT * FROM genres_table")
    fun getGenres(): Flow<List<GenreEntity>>

    @Query(value = "SELECT * FROM platforms_table")
    fun getPlatforms(): Flow<List<PlatformEntity>>

}