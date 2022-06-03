package com.fabledt5.remote.api

import com.fabledt5.remote.api.dto.game_creators.GameDevelopersResponse
import com.fabledt5.remote.api.dto.game_details.GameDetailsResponse
import com.fabledt5.remote.api.dto.game_screenshots.GameScreenshotsResult
import com.fabledt5.remote.api.dto.game_trailers.GameTrailersResponse
import com.fabledt5.remote.api.dto.list_of_games.GamesListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GamesApi {

    @GET(value = "api/games")
    suspend fun getGamesList(
        @Query(value = "page") page: Int,
        @Query(value = "page_size") pageSize: Int,
        @Query(value = "search") search: String? = null,
        @Query(value = "platforms") platforms: String? = null,
        @Query(value = "genres") genres: String? = null,
        @Query(value = "developers") developers: String? = null,
        @Query(value = "dates") dates: String? = null,
        @Query(value = "metacritic") metacriticRatings: String? = null
    ): GamesListResponse

    @GET(value = "api/games/{id}")
    suspend fun getGameDetails(
        @Path(value = "id") gameId: Int
    ): GameDetailsResponse

    @GET(value = "api/games/{id}/movies")
    suspend fun getGameTrailers(
        @Path(value = "id") gameId: Int
    ): GameTrailersResponse

    @GET(value = "api/games/{game_pk}/screenshots")
    suspend fun getGameSnapshots(
        @Path(value = "game_pk") gameId: Int
    ): GameScreenshotsResult

    @GET(value = "api/games/{game_pk}/development-team")
    suspend fun getGameCreators(
        @Path(value = "game_pk") gameId: Int
    ): GameDevelopersResponse

}