package com.fabledt5.db.dao

import androidx.room.*
import com.fabledt5.db.entities.HotGameEntity
import com.fabledt5.db.entities.SavedGameEntity
import com.fabledt5.db.entities.refrences.FavoriteGamesRef
import com.fabledt5.db.entities.refrences.PlayedGamesRef
import com.fabledt5.domain.model.GameType
import kotlinx.coroutines.flow.Flow

@Dao
interface GamesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHotGames(games: List<HotGameEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSavedGame(game: SavedGameEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteGame(favoriteGame: FavoriteGamesRef)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlayedGame(playedGamesRef: PlayedGamesRef)

    @Query(value = "SELECT * FROM hot_games_table")
    fun getHotGames(): Flow<List<HotGameEntity>>

    @Query(
        value = "SELECT * FROM saved_games_table " +
                "INNER JOIN played_games_table ON played_games_table.game_id = saved_games_table.game_id"
    )
    fun readPlayedGames(): Flow<List<SavedGameEntity>>

    @Query(
        value = "SELECT * FROM saved_games_table " +
                "INNER JOIN favorite_games_table ON favorite_games_table.game_id = saved_games_table.game_id"
    )
    fun readFavoriteGames(): Flow<List<SavedGameEntity>>

    @Transaction
    suspend fun fetchGames(savedGameEntity: SavedGameEntity, gameType: GameType) {
        require(gameType == GameType.PLAYED || gameType == GameType.FAVORITE)

        val gameId = savedGameEntity.gameId
        insertSavedGame(savedGameEntity)
        if (gameType == GameType.PLAYED) insertPlayedGame(PlayedGamesRef(gameId = gameId))
        else insertFavoriteGame(FavoriteGamesRef(gameId = gameId))
    }

    @Transaction
    suspend fun fetchHotGames(games: List<HotGameEntity>) {
        deleteHotGames()
        insertHotGames(games = games)
    }

    @Transaction
    suspend fun removeSavedGame(gameId: Int, gameType: GameType) {
        require(gameType == GameType.PLAYED || gameType == GameType.FAVORITE)

        deleteSavedGame(gameId)
        if (gameType == GameType.PLAYED) deletePlayedGameReference(gameId)
        else deleteFavoriteGameReference(gameId)
    }

    @Query(value = "DELETE FROM hot_games_table")
    suspend fun deleteHotGames()

    @Query(value = "DELETE FROM saved_games_table WHERE game_id = :gameId")
    suspend fun deleteSavedGame(gameId: Int)

    @Query(value = "DELETE FROM played_games_table WHERE game_id = :gameId")
    suspend fun deletePlayedGameReference(gameId: Int)

    @Query(value = "DELETE FROM favorite_games_table WHERE game_id = :gameId")
    suspend fun deleteFavoriteGameReference(gameId: Int)

}