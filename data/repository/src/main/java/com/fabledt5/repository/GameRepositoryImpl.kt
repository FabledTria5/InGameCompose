package com.fabledt5.repository

import com.fabledt5.domain.model.GameItem
import com.fabledt5.domain.model.Resource
import com.fabledt5.domain.model.ReviewItem
import com.fabledt5.domain.repository.GameRepository
import com.fabledt5.domain.utlis.setScale
import com.fabledt5.domain.utlis.toPEGI
import com.fabledt5.mapper.toDomain
import com.fabledt5.remote.api.GamesService
import com.fabledt5.remote.parser.ReviewsParser
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val gamesService: GamesService,
    private val reviewsParser: ReviewsParser
) : GameRepository {

    override fun getGameDetails(gameId: Int) = flow {
        emit(Resource.Loading)

        val gameItem = coroutineScope {
            val gameResponse = async { gamesService.getGameDetails(gameId = gameId) }
            val gameTrailerResponse = async { gamesService.getGameTrailers(gameId = gameId) }
            val gameDevelopersTeamResponse =
                async { gamesService.getGameCreators(gameId = gameId) }

            val gameDto = gameResponse.await()
            val gameTrailerDto = gameTrailerResponse.await()
            val gameDevelopersTeamDto = gameDevelopersTeamResponse.await()

            GameItem(
                gameId = gameDto.id,
                gamePoster = gameDto.backgroundImage,
                gameTitle = gameDto.name,
                gamePEGIRating = gameDto.esrbRating?.slug.toPEGI(),
                gameRating = gameDto.rating.setScale(n = 1).toString(),
                gameReleaseYear = gameDto.released.take(n = 4),
                gameGenres = gameDto.genres.take(n = 3).joinToString { it.name },
                gameDeveloper = gameDto.developers.first().name,
                gameDescription = gameDto.description,
                gameTrailersUrls = gameTrailerDto.toDomain(),
                gameRequirements = gameDto.platforms.toDomain(),
                gameDirectors = gameDevelopersTeamDto.results
                    .filter {
                        it.positions.any { position ->
                            position.name.contains("director", ignoreCase = true)
                        }
                    }
                    .take(n = 3)
                    .joinToString { it.name },
                gameWriters = gameDevelopersTeamDto.results
                    .filter {
                        it.positions.any { position ->
                            position.name.contains("writer", ignoreCase = true)
                        }
                    }
                    .take(n = 3)
                    .joinToString { it.name },
                gameReviewsUrl = gameDto.metacriticUrl
            )
        }

        emit(Resource.Success(data = gameItem))
    }

    override fun getGameSnapShots(gameId: Int) = flow {
        emit(Resource.Loading)

        val gameSnapshotsResponse = gamesService.getGameSnapshots(gameId = gameId)
        emit(Resource.Success(data = gameSnapshotsResponse.results.toDomain()))
    }

    override fun getGameReviews(gameUrl: String): List<ReviewItem> {
        val gameReviewsResponse = reviewsParser.parseGameReviews(targetUrl = gameUrl)
        return gameReviewsResponse.toDomain()
    }

}