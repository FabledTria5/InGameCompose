package com.fabledt5.remote.api.dto.game_creators

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class GameCreatorsResult(
    @SerialName("games_count")
    val gamesCount: Int,
    @SerialName("id")
    val id: Int,
    @SerialName("image")
    val image: String?,
    @SerialName("image_background")
    val imageBackground: String,
    @SerialName("name")
    val name: String,
    @SerialName("positions")
    val positions: List<CreatorPosition>,
    @SerialName("slug")
    val slug: String
)