package com.fabledt5.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.fabledt5.db.entities.DeveloperEntity
import com.fabledt5.db.entities.GenreEntity
import com.fabledt5.db.entities.PlatformEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FiltersDao {

    @Query(value = "SELECT * FROM developers_table")
    fun getDevelopers(): Flow<List<DeveloperEntity>>

    @Query(value = "SELECT * FROM genres_table")
    fun getGenres(): Flow<List<GenreEntity>>

    @Query(value = "SELECT * FROM platforms_table")
    fun getPlatforms(): Flow<List<PlatformEntity>>

    @Transaction
    suspend fun setFavoritePlatform(platformId: Int) {
        removeFavoritePlatform()
        setFavorite(platformId)
    }

    @Query(value = "UPDATE platforms_table SET isFavorite = 1 WHERE platform_id = :platformId")
    suspend fun setFavorite(platformId: Int)

    @Query(value = "UPDATE platforms_table SET isFavorite = 0 WHERE isFavorite = 1")
    suspend fun removeFavoritePlatform()

    @Query(value = "SELECT * FROM platforms_table WHERE isFavorite = 1")
    fun getFavoritePlatform(): Flow<PlatformEntity?>

}