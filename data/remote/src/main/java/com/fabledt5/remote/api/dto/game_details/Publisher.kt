package com.fabledt5.remote.api.dto.game_details

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Publisher(
    @SerialName("games_count")
    val gamesCount: Int,
    @SerialName("id")
    val id: Int,
    @SerialName("image_background")
    val imageBackground: String,
    @SerialName("name")
    val name: String,
    @SerialName("slug")
    val slug: String
)