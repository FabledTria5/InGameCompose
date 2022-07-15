package com.fabledt5.db.dao

import androidx.room.*
import com.fabledt5.db.entities.HotGameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GamesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHotGames(games: List<HotGameEntity>)

    @Query(value = "SELECT * FROM games_table WHERE game_type = :gameTypeOrdinal")
    fun getGames(gameTypeOrdinal: Int): Flow<List<HotGameEntity>>

    @Transaction
    suspend fun fetchHotGames(games: List<HotGameEntity>) {
        clearHotGamesGames()
        insertHotGames(games = games)
    }

    @Query(value = "DELETE FROM games_table WHERE game_type = 0")
    suspend fun clearHotGamesGames()

}