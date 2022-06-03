package com.fabledt5.repository.firebase

import com.fabledt5.domain.model.Resource
import com.fabledt5.domain.repository.firebase.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(private val authenticator: FirebaseAuth) :
    AuthRepository {

    override fun isUserAuthenticatedInFirebase() = authenticator.currentUser != null

    override fun signUpFirebase(email: String, password: String) = flow {
        try {
            emit(Resource.Loading)
            authenticator.createUserWithEmailAndPassword(email, password).await()
            emit(Resource.Success(data = authenticator.currentUser?.uid))
        } catch (e: Exception) {
            emit(Resource.Error(exception = e))
        }
    }

    override fun signInFirebase(email: String, password: String) = flow {
        try {
            emit(Resource.Loading)
            authenticator.signInWithEmailAndPassword(email, password).await()
            emit(Resource.Success(data = true))
        } catch (e: Exception) {
            emit(Resource.Error(exception = e))
        }
    }

    override fun resetPassword(email: String) = flow {
        try {
            emit(Resource.Loading)
            authenticator.sendPasswordResetEmail(email).await()
            emit(Resource.Success(data = true))
        } catch (e: Exception) {
            emit(Resource.Error(exception = e))
        }
    }

    override fun signOut() = flow {
        try {
            emit(Resource.Loading)
            authenticator.currentUser?.delete()?.await()
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Error(exception = e))
        }
    }

    override fun getFirebaseAuthState() = callbackFlow {
        val authStateListener = FirebaseAuth.AuthStateListener { auth ->
            trySend(auth.currentUser == null)
        }
        authenticator.addAuthStateListener(authStateListener)
        awaitClose {
            authenticator.removeAuthStateListener(authStateListener)
        }
    }

}