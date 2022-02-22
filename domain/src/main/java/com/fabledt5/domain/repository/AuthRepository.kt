package com.fabledt5.domain.repository

import com.fabledt5.domain.model.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun isUserAuthenticatedInFirebase(): Boolean

    fun signInFirebase(email: String, password: String): Flow<Resource<Boolean>>

    fun signUpFirebase(email: String, password: String): Flow<Resource<String?>>

    fun resetPassword(email: String): Flow<Resource<Boolean>>

    fun signOut(): Flow<Resource<Boolean>>

    fun getFirebaseAuthState(): Flow<Boolean>
}