package com.fabledt5.remote.api.dto.platforms_list

data class PlatformsListResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Result>
)