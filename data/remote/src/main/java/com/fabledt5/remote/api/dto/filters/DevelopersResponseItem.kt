package com.fabledt5.remote.api.dto.filters

import kotlinx.serialization.Serializable

@Serializable
data class DevelopersResponseItem(
    val developerFoundation: Int,
    val developerHeadquarters: String,
    val developerIcon: String,
    val developerKeyPeople: String,
    val developerName: String,
    val developerPreview: String,
    val developerWebsite: String
)