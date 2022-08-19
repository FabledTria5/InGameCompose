package com.fabledt5.domain.model

data class ValidationResult(
    val isSuccessful: Boolean,
    val errorMessage: String? = null
)
