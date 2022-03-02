package com.fabledt5.remote.dto.platforms_list

data class Result(
    val games_count: Int,
    val id: Int,
    val image: String?,
    val image_background: String?,
    val name: String,
    val slug: String,
    val year_end: Int?,
    val year_start: Int?
)