package com.fabledt5.domain.utlis

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.SimpleDateFormat
import java.util.*

fun String?.toPEGI() = when (this) {
    ESRBRatings.everyone.name -> "7+"
    ESRBRatings.`everyone-10-plus`.name -> "12+"
    ESRBRatings.teen.name -> "16+"
    ESRBRatings.mature.name -> "16+"
    ESRBRatings.`adults-only`.name -> "18+"
    else -> "16+"
}

fun Double.setScale(n: Int): BigDecimal = BigDecimal(this).setScale(n, RoundingMode.HALF_DOWN)

fun String?.getDateAsString(): String {
    if (this == null) return "Unknown"
    val remoteDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
    val resultDateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH)

    val date = remoteDateFormat.parse(this)
    return date?.let {
        resultDateFormat.format(it)
    } ?: "Unknown"
}

