package com.fabledt5.remote.parser

import com.fabledt5.remote.parser.dto.GameReviewDto
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import javax.inject.Inject

class ReviewsParserImpl @Inject constructor() : ReviewsParser {

    companion object {
        private const val SORTING_PARAMETER = "critic-reviews?sort-by=publication"
    }

    override fun parseGameReviews(targetUrl: String): List<GameReviewDto> {
        val gameReviewsUrl = targetUrl + SORTING_PARAMETER

        val result = Jsoup.connect(gameReviewsUrl).get()
        val rawReviewsList = result.select("li.review.critic_review")

        return extractReviews(rawReviewsList)
    }


    private fun extractReviews(reviewsElements: List<Element>): List<GameReviewDto> {
        val reviewsList = arrayListOf<GameReviewDto>()

        for (element in reviewsElements) {
            val metascore = element.select(".metascore_w.medium.game.positive.indiv").text()
            val criticName = element.select(".source").text()
            val reviewBody = element.select(".review_body").text()
            val reviewDate = element.select(".date").text()

            val gameReviewDto = GameReviewDto(
                criticScore = metascore,
                criticName = criticName,
                reviewText = reviewBody,
                reviewDate = reviewDate
            )
            reviewsList.add(gameReviewDto)
        }
        return reviewsList
    }
}