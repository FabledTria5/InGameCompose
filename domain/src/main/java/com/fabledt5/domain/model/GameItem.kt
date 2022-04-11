package com.fabledt5.domain.model

data class GameItem(
    val gameId: Int,
    val gamePoster: String? = null,
    val gameTitle: String = "",
    val gameDeveloper: String = "",
    val gamePEGIRating: String = "",
    val gameReleaseYear: String = "",
    val gameGenres: String = "",
    val gameDescription: String = "",
    val gameRequirements: GameRequirements? = null,
    val gameDirectors: String = "",
    val gameWriters: String = "",
    val gameTrailersUrl: String = "",
    val gameReviewsUrl: String? = null
)