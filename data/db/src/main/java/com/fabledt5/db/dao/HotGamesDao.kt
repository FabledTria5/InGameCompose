package com.fabledt5.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.fabledt5.db.entities.HotGameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HotGamesDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertHotGames(games: List<HotGameEntity>)

    @Query(value = "SELECT * FROM hot_games_table")
    fun getHotGames(): List<HotGameEntity>

    @Query(value = "DELETE FROM hot_games_table")
    suspend fun clearHotGames()

}