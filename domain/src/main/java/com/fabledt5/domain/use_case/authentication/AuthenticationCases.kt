package com.fabledt5.domain.use_case.authentication

import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
data class AuthenticationCases @Inject constructor (
    val authenticateUser: AuthenticateUser,
    val checkAuthentication: CheckAuthentication,
    val registerUser: RegisterUser
)