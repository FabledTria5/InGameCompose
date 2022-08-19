package com.fabledt5.authentication.model

sealed class AuthenticationFormEvent {
    data class EmailChanged(val email: String) : AuthenticationFormEvent()
    data class PasswordChanged(val password: String) : AuthenticationFormEvent()
    data class NicknameChanged(val nickname: String): AuthenticationFormEvent()

    object Submit : AuthenticationFormEvent()
}
