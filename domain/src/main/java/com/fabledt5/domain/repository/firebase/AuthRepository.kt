package com.fabledt5.domain.repository.firebase

import com.fabledt5.domain.model.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun signInFirebase(email: String, password: String): Resource<Boolean>

    suspend fun signUpFirebase(email: String, password: String): Resource<String?>

    suspend fun resetPassword(email: String): Resource<Boolean>

    suspend fun signOut(): Resource<Boolean>

    fun isUserAuthenticated(): Flow<Boolean>
}