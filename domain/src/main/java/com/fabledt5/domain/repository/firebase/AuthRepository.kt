package com.fabledt5.domain.repository.firebase

import com.fabledt5.domain.model.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    val isUserAuthenticated: Boolean

    suspend fun signInFirebase(email: String, password: String): Resource<Boolean>

    suspend fun signUpFirebase(email: String, password: String): Resource<Boolean>

    suspend fun resetPassword(email: String): Resource<Boolean>

    suspend fun signOut(): Resource<Boolean>

    fun checkAuthStatus(): Flow<Boolean>
}