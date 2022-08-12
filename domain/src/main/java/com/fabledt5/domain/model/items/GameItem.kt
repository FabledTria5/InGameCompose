package com.fabledt5.domain.model.items

data class GameItem(
    val gameId: Int,
    val gamePoster: String? = null,
    val gameTitle: String = "",
    val gameDeveloper: String = "",
    val gamePEGIRating: String = "",
    val releaseDate: String = "",
    val gameLastUpdate: String = "",
    val gameGenres: String = "",
    val gameDescription: String = "",
    val requirementsItem: RequirementsItem? = null,
    val gameDirectors: String = "",
    val gameWriters: String = "",
    val gameReviewsUrl: String? = null
)