package com.fabledt5.remote.api.dto.game_details

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class EsrbRating(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("slug")
    val slug: String?
)