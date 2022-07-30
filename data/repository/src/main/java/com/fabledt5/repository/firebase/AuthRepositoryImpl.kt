package com.fabledt5.repository.firebase

import com.fabledt5.domain.model.Resource
import com.fabledt5.domain.repository.ErrorRepository
import com.fabledt5.domain.repository.firebase.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val authenticator: FirebaseAuth,
    private val errorRepository: ErrorRepository
) : AuthRepository {

    override val isUserAuthenticated: Boolean
        get() = authenticator.currentUser != null

    override suspend fun signUpFirebase(email: String, password: String) = try {
        val result = authenticator.createUserWithEmailAndPassword(email, password).await()
        Resource.Success(data = result.user?.uid)
    } catch (exception: FirebaseAuthException) {
        Timber.e(exception)
        val error = errorRepository.resolveAuthenticationError(exception)
        Resource.Error(error)
    }

    override suspend fun signInFirebase(email: String, password: String) = try {
        authenticator.signInWithEmailAndPassword(email, password).await()
        Resource.Success(data = true)
    } catch (exception: FirebaseAuthException) {
        Timber.e(exception)
        val error = errorRepository.resolveAuthenticationError(exception)
        Resource.Error(error)
    }

    override suspend fun resetPassword(email: String) = try {
        authenticator.sendPasswordResetEmail(email).await()
        Resource.Success(data = true)
    } catch (exception: FirebaseAuthException) {
        Timber.e(exception)
        val error = errorRepository.resolveAuthenticationError(exception)
        Resource.Error(error)
    }

    override suspend fun signOut() = try {
        authenticator.currentUser?.delete()?.await()
        Resource.Success(true)
    } catch (exception: FirebaseAuthException) {
        Timber.e(exception)
        val error = errorRepository.resolveAuthenticationError(exception)
        Resource.Error(error)
    }

    override fun checkAuthStatus() = callbackFlow {
        val authStateListener = FirebaseAuth.AuthStateListener { auth ->
            trySend(element = auth.currentUser != null)
        }
        authenticator.addAuthStateListener(authStateListener)
        awaitClose {
            authenticator.removeAuthStateListener(authStateListener)
        }
    }

}