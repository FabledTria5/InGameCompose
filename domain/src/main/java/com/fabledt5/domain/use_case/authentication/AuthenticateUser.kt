package com.fabledt5.domain.use_case.authentication

import com.fabledt5.domain.repository.AuthRepository
import javax.inject.Inject

class AuthenticateUser @Inject constructor(private val authRepository: AuthRepository) {

    operator fun invoke(userEmail: String, userPassword: String) =
        authRepository.signInFirebase(email = userEmail, password = userPassword)

}