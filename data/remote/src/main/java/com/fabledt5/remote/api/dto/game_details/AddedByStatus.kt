package com.fabledt5.remote.api.dto.game_details

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class AddedByStatus(
    @SerialName("beaten")
    val beaten: Int,
    @SerialName("dropped")
    val dropped: Int,
    @SerialName("owned")
    val owned: Int,
    @SerialName("playing")
    val playing: Int,
    @SerialName("toplay")
    val toplay: Int,
    @SerialName("yet")
    val yet: Int
)