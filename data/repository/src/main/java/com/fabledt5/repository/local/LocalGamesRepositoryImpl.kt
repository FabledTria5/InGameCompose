package com.fabledt5.repository.local

import com.fabledt5.db.dao.GamesDao
import com.fabledt5.domain.model.GameType
import com.fabledt5.domain.model.items.GameItem
import com.fabledt5.domain.repository.LocalGamesRepository
import com.fabledt5.mapper.toDomain
import com.fabledt5.mapper.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import timber.log.Timber
import javax.inject.Inject

class LocalGamesRepositoryImpl @Inject constructor(
    private val gamesDao: GamesDao
) : LocalGamesRepository {

    override fun readHotGames(): Flow<List<GameItem>> =
        gamesDao.getHotGames().toDomain()

    override fun readSavedGames(gameType: GameType): Flow<List<GameItem>> = when (gameType) {
        GameType.FAVORITE -> gamesDao.readFavoriteGames().toDomain()
        GameType.PLAYED -> gamesDao.readPlayedGames().toDomain()
        GameType.HOT_GAME -> {
            Timber.i(message = "Hot games can't be observed from here")
            flowOf()
        }
    }

    override suspend fun insertGame(gameData: Pair<GameItem, GameType>) =
        gamesDao.fetchGames(gameData.first.toEntity(), gameData.second)

    override suspend fun deleteGame(gameId: Int, gameType: GameType) =
        gamesDao.removeSavedGame(gameId, gameType)

}


