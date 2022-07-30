package com.fabledt5.db.dao

import androidx.room.*
import com.fabledt5.db.entities.HotGameEntity
import com.fabledt5.db.entities.SavedGameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GamesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHotGames(games: List<HotGameEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSavedGame(game: SavedGameEntity)

    @Query(value = "SELECT * FROM games_table WHERE game_type = :gameTypeOrdinal")
    fun getHotGames(gameTypeOrdinal: Int): Flow<List<HotGameEntity>>

    @Query(value = "SELECT * FROM saved_games_table WHERE game_type = :gameTypeOrdinal")
    fun readSavedGames(gameTypeOrdinal: Int): Flow<List<SavedGameEntity>>

    @Transaction
    suspend fun fetchHotGames(games: List<HotGameEntity>) {
        clearHotGamesGames()
        insertHotGames(games = games)
    }

    @Query(value = "DELETE FROM games_table WHERE game_type = 0")
    suspend fun clearHotGamesGames()

    @Query(value = "DELETE FROM saved_games_table WHERE game_id = :gameId")
    suspend fun deleteGame(gameId: Int)

}