package com.fabledt5.remote.api.dto.list_of_games

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EsrbRating(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("slug")
    val slug: String?
)