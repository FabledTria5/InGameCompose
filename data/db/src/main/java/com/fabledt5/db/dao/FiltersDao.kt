package com.fabledt5.db.dao

import androidx.room.*
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

    @Transaction
    suspend fun setFavoritePlatform(platformId: Int) {
        val currentFavoritePlatform = getFavoritePlatform(platformId)
        if (currentFavoritePlatform != null) removeFavoritePlatform(platformId)

        setFavorite(platformId)
    }

    @Query(value = "UPDATE platforms_table SET isFavorite = 'true' WHERE platform_id = :platformId")
    suspend fun setFavorite(platformId: Int)

    @Query(value = "UPDATE platforms_table SET isFavorite = 'false' WHERE platform_id = :platformId")
    suspend fun removeFavoritePlatform(platformId: Int)

    @Query(value = "SELECT * FROM platforms_table WHERE platform_id = :platformId AND isFavorite = 'true'")
    suspend fun getFavoritePlatform(platformId: Int): PlatformEntity?

    @Query(value = "SELECT * FROM platforms_table WHERE isFavorite = 'true'")
    fun observeFavoritePlatform(): Flow<PlatformEntity?>

    @Query(value = "SELECT * FROM developers_table")
    fun getDevelopers(): Flow<List<DeveloperEntity>>

    @Query(value = "SELECT * FROM genres_table")
    fun getGenres(): Flow<List<GenreEntity>>

    @Query(value = "SELECT * FROM platforms_table")
    fun getPlatforms(): Flow<List<PlatformEntity>>

}