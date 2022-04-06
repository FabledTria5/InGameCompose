package com.fabledt5.domain.model

data class GameRating(
    val gameRating: String,
    val gameReviews: List<ReviewItem>
)