package com.fabledt5.remote.parser

import com.fabledt5.remote.parser.dto.GameReviewDto

interface ReviewsParser {

    fun parseGameReviews(targetUrl: String): List<GameReviewDto>

}