package com.fabledt5.repository.remote

import com.fabledt5.domain.model.Resource
import com.fabledt5.domain.model.items.GameItem
import com.fabledt5.domain.model.items.RatingItem
import com.fabledt5.domain.repository.ErrorRepository
import com.fabledt5.domain.repository.GameRepository
import com.fabledt5.domain.utlis.getDateAsString
import com.fabledt5.domain.utlis.toPEGI
import com.fabledt5.mapper.toDomain
import com.fabledt5.remote.api.GamesApi
import com.fabledt5.remote.api.dto.game_creators.GameCreatorsResponse
import com.fabledt5.remote.api.dto.game_details.GameDetailsResponse
import com.fabledt5.remote.api.dto.game_trailers.GameTrailersResponse
import com.fabledt5.remote.parser.ReviewsParser
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import retrofit2.HttpException
import timber.log.Timber
import java.net.SocketTimeoutException
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val gamesApi: GamesApi,
    private val reviewsParser: ReviewsParser,
    private val errorRepository: ErrorRepository
) : GameRepository {

    override suspend fun getGameDetails(gameId: Int): Resource<GameItem> = coroutineScope {
        val gameResponse = async { gamesApi.getGameDetails(gameId = gameId) }
        val gameTrailerResponse = async { gamesApi.getGameTrailers(gameId = gameId) }
        val gameDevelopersTeamResponse =
            async { gamesApi.getGameCreators(gameId = gameId) }

        try {
            val gameDto = gameResponse.await()
            val gameTrailerDto = gameTrailerResponse.await()
            val gameDevelopersTeamDto = gameDevelopersTeamResponse.await()

            val gameItem = buildGameItem(gameDto, gameTrailerDto, gameDevelopersTeamDto)
            Resource.Success(gameItem)
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

    override suspend fun getGameSnapShots(gameId: Int): Resource<List<String>> = try {
        val snapshots = gamesApi.getGameSnapshots(gameId = gameId)
        Resource.Success(snapshots.toDomain())
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

    override fun getGameReviews(gameUrl: String): Resource<RatingItem> = try {
        val gameReviews = reviewsParser.parseGameReviews(targetUrl = gameUrl)
        Resource.Success(gameReviews.toDomain())
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

    private fun buildGameItem(
        gameDto: GameDetailsResponse,
        trailersDto: GameTrailersResponse,
        developersDto: GameCreatorsResponse
    ): GameItem = GameItem(
        gameId = gameDto.id,
        gamePoster = gameDto.backgroundImage,
        gameTitle = gameDto.name,
        gamePEGIRating = gameDto.esrbRating?.slug.toPEGI(),
        gameReleaseYear = gameDto.released.getDateAsString(),
        gameGenres = gameDto.genres.take(n = 3).joinToString { it.name },
        gameDeveloper = gameDto.developers.firstOrNull()?.name ?: "Unknown",
        gameDescription = gameDto.description,
        gameTrailersUrl = trailersDto.toDomain(),
        requirementsItem = gameDto.platforms.toDomain(),
        gameDirectors = developersDto.results
            .filter { result ->
                result.positions.any { position ->
                    position.name.contains("director", ignoreCase = true)
                }
            }
            .take(n = 3)
            .joinToString { it.name },
        gameWriters = developersDto.results
            .filter { result ->
                result.positions.any { position ->
                    position.name.contains("writer", ignoreCase = true)
                }
            }
            .take(n = 3)
            .joinToString { it.name },
        gameReviewsUrl = gameDto.metacriticUrl
    )

}