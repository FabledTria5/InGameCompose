package com.fabledt5.domain.use_case.authentication

import com.fabledt5.domain.repository.firebase.AuthRepository
import javax.inject.Inject

class IsUserAuthenticated @Inject constructor(private val authRepository: AuthRepository) {

    operator fun invoke() = authRepository.isUserAuthenticatedInFirebase()

}