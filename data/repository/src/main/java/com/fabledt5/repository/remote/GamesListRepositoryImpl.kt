package com.fabledt5.repository.remote

import com.fabledt5.db.dao.GamesDao
import com.fabledt5.domain.model.GameType
import com.fabledt5.domain.model.Resource
import com.fabledt5.domain.model.items.GameItem
import com.fabledt5.domain.repository.ErrorRepository
import com.fabledt5.domain.repository.GamesListRepository
import com.fabledt5.mapper.toDomain
import com.fabledt5.mapper.toDomainShort
import com.fabledt5.mapper.toEntity
import com.fabledt5.remote.api.GamesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import retrofit2.HttpException
import timber.log.Timber
import java.net.SocketTimeoutException
import java.util.*
import javax.inject.Inject

class GamesListRepositoryImpl @Inject constructor(
    private val gamesApi: GamesApi,
    private val gamesDao: GamesDao,
    private val errorRepository: ErrorRepository
) : GamesListRepository {

    override fun getHotGames(
        gamesCount: Int,
        dates: String,
        metacriticRatings: String
    ): Flow<List<GameItem>> = gamesDao.getHotGames(GameType.HOT_GAME.ordinal)
        .onEach { list ->
            if (list.isNotEmpty()) {
                val calendar = Calendar.getInstance()
                val currentMonth = calendar.get(Calendar.MONTH)
                calendar.time = Date(list.first().createdAt)
                val gamesMonth = calendar.get(Calendar.MONTH)

                if (currentMonth > gamesMonth) {
                    fetchHotGames(
                        gamesCount = gamesCount,
                        dates = dates,
                        metacriticRatings = metacriticRatings
                    )
                }
                calendar.time = Date()
            } else fetchHotGames(
                gamesCount = gamesCount,
                dates = dates,
                metacriticRatings = metacriticRatings
            )
        }.toDomain()

    private suspend fun fetchHotGames(gamesCount: Int, dates: String, metacriticRatings: String) {
        val hotGamesResponse = gamesApi.getGamesList(
            page = 1,
            pageSize = gamesCount,
            dates = dates,
            metacriticRatings = metacriticRatings
        )
        gamesDao.fetchHotGames(hotGamesResponse.toEntity())
    }

    override suspend fun getUpcomingGames(
        dates: String,
        platformId: Int,
        gamesCount: Int
    ): Resource<List<GameItem>> = try {
        val upcomingGames = gamesApi.getGamesList(
            dates = dates,
            pageSize = gamesCount,
            platforms = platformId.toString(),
            page = 1
        )
        Resource.Success(upcomingGames.toDomainShort())
    } catch (exception: SocketTimeoutException) {
        Timber.e(exception)
        val error =
            errorRepository.resolveNetworkErrorError(errorCode = -1, exception = exception)
        Resource.Error(error)
    } catch (exception: HttpException) {
        Timber.e(exception)
        val errorCode = exception.code()
        val error = errorRepository.resolveNetworkErrorError(errorCode = errorCode)
        Resource.Error(error)
    }

    override suspend fun getBestGames(
        ratings: String,
        platformId: Int,
        gamesCount: Int
    ): Resource<List<GameItem>> = try {
        val bestGames = gamesApi.getGamesList(
            metacriticRatings = ratings,
            pageSize = gamesCount,
            page = 1,
            platforms = platformId.toString()
        )
        Resource.Success(bestGames.toDomainShort())
    } catch (exception: SocketTimeoutException) {
        Timber.e(exception)
        val error =
            errorRepository.resolveNetworkErrorError(errorCode = -1, exception = exception)
        Resource.Error(error)
    } catch (exception: HttpException) {
        Timber.e(exception)
        val errorCode = exception.code()
        val error = errorRepository.resolveNetworkErrorError(errorCode = errorCode)
        Resource.Error(error)
    }

    override suspend fun getLatestGames(
        dates: String,
        platformId: Int,
        gamesCount: Int
    ): Resource<List<GameItem>> = try {
        val latestGames = gamesApi.getGamesList(
            dates = dates,
            pageSize = gamesCount,
            page = 1,
            platforms = platformId.toString()
        )
        Resource.Success(latestGames.toDomainShort())
    } catch (exception: SocketTimeoutException) {
        Timber.e(exception)
        val error =
            errorRepository.resolveNetworkErrorError(errorCode = -1, exception = exception)
        Resource.Error(error)
    } catch (exception: HttpException) {
        Timber.e(exception)
        val errorCode = exception.code()
        val error = errorRepository.resolveNetworkErrorError(errorCode = errorCode)
        Resource.Error(error)
    }

    override suspend fun getGamesByDate(date: String): Resource<List<GameItem>> = try {
        val games = gamesApi.getGamesList(dates = date)
        Resource.Success(games.toDomainShort())
    } catch (exception: SocketTimeoutException) {
        Timber.e(exception)
        val error =
            errorRepository.resolveNetworkErrorError(errorCode = -1, exception = exception)
        Resource.Error(error)
    } catch (exception: HttpException) {
        Timber.e(exception)
        val errorCode = exception.code()
        val error = errorRepository.resolveNetworkErrorError(errorCode = errorCode)
        Resource.Error(error)
    }


}
