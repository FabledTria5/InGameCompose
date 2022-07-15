package com.fabledt5.domain.use_case.authentication

import com.fabledt5.domain.model.Resource
import com.fabledt5.domain.repository.firebase.AuthRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthenticateUser @Inject constructor(private val authRepository: AuthRepository) {

    operator fun invoke(userEmail: String, userPassword: String) = flow {
        emit(Resource.Loading)
        when (val authenticationResult =
            authRepository.signInFirebase(email = userEmail, password = userPassword)) {
            is Resource.Error -> emit(authenticationResult)
            else -> Unit
        }
    }

}