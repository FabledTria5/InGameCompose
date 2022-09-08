package com.fabledt5.utils

import android.content.Context
import com.fabledt5.domain.model.DefaultErrors
import com.fabledt5.domain.model.ErrorItem
import com.fabledt5.domain.repository.ErrorRepository
import com.fabledt5.repository.R
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.firestore.FirebaseFirestoreException
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.concurrent.TimeoutException
import javax.inject.Inject

class ErrorRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : ErrorRepository {

    sealed class NetworkErrors {
        class ForbiddenError(override val errorMessage: String) : ErrorItem()
        class BadRequestError(override val errorMessage: String) : ErrorItem()
        class UnAuthorizedError(override val errorMessage: String) : ErrorItem()
        class NotFoundError(override val errorMessage: String) : ErrorItem()
        class ServerError(override val errorMessage: String) : ErrorItem()
        class TimeoutError(override val errorMessage: String) : ErrorItem()
    }

    sealed class AuthenticationErrors {
        class NotExists(override val errorMessage: String) : ErrorItem()
        class InvalidCredentials(override val errorMessage: String) : ErrorItem()
        class AlreadyExists(override val errorMessage: String) : ErrorItem()
    }

    sealed class RemoteDataStoreErrors {
        class Cancelled(override val errorMessage: String) : ErrorItem()
        class AlreadyExists(override val errorMessage: String) : ErrorItem()
        class ServerError(override val errorMessage: String) : ErrorItem()
    }

    override fun resolveNetworkErrorError(errorCode: Int, exception: Exception?): ErrorItem {
        (exception as? TimeoutException)?.let {
            return NetworkErrors.TimeoutError(context.getString(R.string.error_timeout))
        }
        return when (errorCode) {
            400 -> NetworkErrors.BadRequestError(context.getString(R.string.error_bad_request))
            401 -> NetworkErrors.UnAuthorizedError(context.getString(R.string.error_unauthorized))
            403 -> NetworkErrors.ForbiddenError(context.getString(R.string.error_forbidden))
            404 -> NetworkErrors.NotFoundError(context.getString(R.string.error_not_found))
            in 500..600 -> NetworkErrors.ServerError(context.getString(R.string.error_sever))
            else -> DefaultErrors.UnknownError(context.getString(R.string.error_unknown))
        }
    }

    override fun resolveAuthenticationError(exception: Exception): ErrorItem {
        require(exception is FirebaseAuthException)
        return when (exception) {
            is FirebaseAuthInvalidUserException -> AuthenticationErrors.NotExists(
                context.getString(R.string.error_user_not_exists)
            )
            is FirebaseAuthInvalidCredentialsException -> AuthenticationErrors.InvalidCredentials(
                context.getString(R.string.error_invalid_credentials)
            )
            is FirebaseAuthUserCollisionException -> AuthenticationErrors.AlreadyExists(
                context.getString(R.string.error_existing_user)
            )
            else -> DefaultErrors.UnknownError(context.getString(R.string.error_unknown))
        }
    }

    override fun resolveRemoteUserDataError(exception: Exception): ErrorItem {
        require(exception is FirebaseFirestoreException)
        return when (exception.code) {
            FirebaseFirestoreException.Code.CANCELLED ->
                RemoteDataStoreErrors.Cancelled(context.getString(R.string.error_operation_cancelled))
            FirebaseFirestoreException.Code.UNKNOWN ->
                DefaultErrors.UnknownError(context.getString(R.string.error_unknown))
            FirebaseFirestoreException.Code.ALREADY_EXISTS ->
                RemoteDataStoreErrors.AlreadyExists(context.getString(R.string.error_resource_already_exists))
            else -> RemoteDataStoreErrors.ServerError(context.getString(R.string.error_sever))
        }
    }

    override fun resolveEmptyEmail(): String = context.getString(R.string.error_empty_email)

    override fun resolveIncorrectEmail(): String = context.getString(R.string.error_incorrect_email)

    override fun resolveEmptyPassword(): String = context.getString(R.string.error_empty_password)

    override fun resolveShortPassword(): String = context.getString(R.string.error_short_password)

    override fun resolveIncorrectPassword(): String =
        context.getString(R.string.error_incorrect_password)

    override fun resolveEmptyNickname(): String = context.getString(R.string.error_empty_nickname)
}