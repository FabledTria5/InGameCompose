package com.fabledt5.remote.parser.dto

data class GameReviewDto(
    val criticScore: Int,
    val criticName: String,
    val reviewText: String,
    val reviewDate: String
)