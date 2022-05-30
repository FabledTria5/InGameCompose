package com.fabledt5.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.fabledt5.db.entities.HotGameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GamesDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertHotGames(games: List<HotGameEntity>)

    @Query(value = "SELECT * FROM games_table WHERE game_type = :gameTypeOrdinal")
    fun getGames(gameTypeOrdinal: Int): Flow<List<HotGameEntity>>

    @Query(value = "DELETE FROM games_table WHERE game_type = :gameTypeOrdinal")
    suspend fun clearGames(gameTypeOrdinal: Int)

}