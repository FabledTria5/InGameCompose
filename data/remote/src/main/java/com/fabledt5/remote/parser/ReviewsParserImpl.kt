package com.fabledt5.remote.parser

import android.util.Log
import com.fabledt5.remote.parser.dto.GameReviewDto
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import java.net.MalformedURLException
import javax.inject.Inject

class ReviewsParserImpl @Inject constructor() : ReviewsParser {

    companion object {
        private const val SORTING_PARAMETER = "/critic-reviews?sort-by=publication"
        private const val TAG = "ReviewsParserImpl"
    }

    override fun parseGameReviews(targetUrl: String): List<GameReviewDto> {
        val gameReviewsUrl = targetUrl + SORTING_PARAMETER

        val result = try {
            Jsoup.connect(gameReviewsUrl).get()
        } catch (e: MalformedURLException) {
            Log.e(TAG, "parseGameReviews: connection to page failed", e)
            return emptyList()
        }

        val rawReviewsList = result.select("li.review.critic_review")

        return extractReviews(rawReviewsList)
    }


    private fun extractReviews(reviewsElements: List<Element>): List<GameReviewDto> {
        val reviewsList = arrayListOf<GameReviewDto>()

        for (element in reviewsElements) {
            val metaScore = element.select(".metascore_w.medium.game.positive.indiv").text()
            val criticName = element.select(".source").text()
            val reviewBody = element.select(".review_body").text()
            val reviewDate = element.select(".date").text()

            val gameReviewDto = GameReviewDto(
                criticScore = metaScore,
                criticName = criticName,
                reviewText = reviewBody,
                reviewDate = reviewDate
            )
            reviewsList.add(gameReviewDto)
        }
        return reviewsList
    }
}