package com.fabledt5.remote.api

import com.fabledt5.remote.api.dto.filters.DevelopersResponseItem
import com.fabledt5.remote.api.dto.filters.GenresResponseItem
import com.fabledt5.remote.api.dto.filters.PlatformsResponseItem
import retrofit2.http.GET

interface InGameService {

    @GET(value = "/filters/genres")
    suspend fun getGamesGenres(): List<GenresResponseItem>

    @GET(value = "/filters/platforms")
    suspend fun getGamesPlatforms(): List<PlatformsResponseItem>

    @GET(value = "/filters/developers")
    suspend fun getGamesDevelopers(): List<DevelopersResponseItem>

}