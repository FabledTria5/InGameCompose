package com.fabledt5.game.utils

import com.fabledt5.domain.model.items.ReviewItem

fun List<ReviewItem>.toRatingsCounter(): Map<Int, Int> {
    val map = groupingBy { it.reviewerRating }.eachCount()
    val standardMap = mapOf(5 to 0, 4 to 0, 3 to 0, 2 to 0, 1 to 0)

    return (map.keys + standardMap.keys)
        .associateWith {
            if (map[it] != 0) map[it]
            else standardMap[it]
        }
        .toSortedMap(compareByDescending { it })
        .mapValues {
            if (it.value == null) 0
            else it.value!!
        }
}

fun Int?.calculatePercent(size: Int) = this?.toFloat()?.div(size)?.times(100)?.toInt() ?: 0