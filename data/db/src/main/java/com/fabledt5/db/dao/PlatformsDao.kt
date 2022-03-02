package com.fabledt5.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.fabledt5.db.entities.PlatformEntity

@Dao
interface PlatformsDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertPlatforms(platforms: List<PlatformEntity>)

    @Query(value = "SELECT * FROM platforms_table")
    suspend fun getPlatformsList(): List<PlatformEntity>

}