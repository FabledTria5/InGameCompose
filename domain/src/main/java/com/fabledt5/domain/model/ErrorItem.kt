package com.fabledt5.domain.model

abstract class ErrorItem : Exception() {
    abstract val errorMessage: String
}

sealed class DefaultErrors {
    class UnknownError(override val errorMessage: String) : ErrorItem()
}