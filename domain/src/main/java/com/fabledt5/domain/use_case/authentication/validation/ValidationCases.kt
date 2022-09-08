package com.fabledt5.domain.use_case.authentication.validation

import javax.inject.Inject

data class ValidationCases @Inject constructor(
    val validateEmail: ValidateEmail,
    val validatePassword: ValidatePassword,
    val validateNickname: ValidateNickname
)