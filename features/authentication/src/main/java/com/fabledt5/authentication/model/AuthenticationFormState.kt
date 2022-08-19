package com.fabledt5.authentication.model

data class AuthenticationFormState(
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val nickname: String = "",
    val nicknameError: String? = ""
)
