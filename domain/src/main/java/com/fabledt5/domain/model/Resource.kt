package com.fabledt5.domain.model

sealed class Resource<out T> {
    class Success<T>(val data: T) : Resource<T>()
    class Error<T>(val error: ErrorItem, val data: T? = null) : Resource<T>()
    object Loading : Resource<Nothing>()
    object Idle : Resource<Nothing>()
}
