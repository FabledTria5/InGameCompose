package com.fabledt5.domain.utlis

fun String?.toPEGI() = when (this) {
    ESRBRatings.everyone.name -> "7+"
    ESRBRatings.`everyone-10-plus`.name -> "12+"
    ESRBRatings.teen.name -> "16+"
    ESRBRatings.mature.name -> "16+"
    ESRBRatings.`adults-only`.name -> "18+"
    else -> "16+"
}