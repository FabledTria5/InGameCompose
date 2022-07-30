package com.fabledt5.repository.local

import com.fabledt5.db.dao.GamesDao
import com.fabledt5.domain.model.GameType
import com.fabledt5.domain.model.items.GameItem
import com.fabledt5.domain.repository.LocalGamesRepository
import com.fabledt5.mapper.toDomain
import com.fabledt5.mapper.toEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalGamesRepositoryImpl @Inject constructor(
    private val gamesDao: GamesDao
) : LocalGamesRepository {

    override fun readHotGames(): Flow<List<GameItem>> =
        gamesDao.getHotGames(GameType.HOT_GAME.ordinal).toDomain()

    override fun readSavedGames(gameType: GameType): Flow<List<GameItem>> =
        gamesDao.readSavedGames(gameType.ordinal).toDomain()

    override suspend fun insertGame(game: Pair<GameItem, GameType>) =
        gamesDao.insertSavedGame(game.first.toEntity(game.second))

    override suspend fun deleteGame(gameId: Int) = gamesDao.deleteGame(gameId)

}


