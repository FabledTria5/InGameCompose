package com.fabledt5.domain.use_case.authentication

import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
data class AuthenticationCases @Inject constructor (
    val authenticateUser: AuthenticateUser,
    val isUserAuthenticated: IsUserAuthenticated,
    val registerUser: RegisterUser
)