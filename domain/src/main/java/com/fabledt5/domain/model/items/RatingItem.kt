package com.fabledt5.domain.model.items

data class RatingItem(
    val gameRating: String = "",
    val gameReviews: List<ReviewItem> = listOf()
)