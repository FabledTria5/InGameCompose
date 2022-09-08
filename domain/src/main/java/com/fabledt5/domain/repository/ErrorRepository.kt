package com.fabledt5.domain.repository

import com.fabledt5.domain.model.ErrorItem

interface ErrorRepository {

    fun resolveNetworkErrorError(errorCode: Int, exception: Exception? = null): ErrorItem

    fun resolveAuthenticationError(exception: Exception): ErrorItem

    fun resolveRemoteUserDataError(exception: Exception): ErrorItem

    fun resolveEmptyEmail(): String

    fun resolveIncorrectEmail(): String

    fun resolveEmptyPassword(): String

    fun resolveShortPassword(): String

    fun resolveIncorrectPassword(): String
    fun resolveEmptyNickname(): String

}