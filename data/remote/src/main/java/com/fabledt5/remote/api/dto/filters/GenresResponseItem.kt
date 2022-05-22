package com.fabledt5.remote.api.dto.filters

import kotlinx.serialization.Serializable

@Serializable
data class GenresResponseItem(
    val genreId: Int,
    val genreName: String
)