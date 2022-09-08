package com.fabledt5.domain.use_case.authentication.validation

import com.fabledt5.domain.model.ValidationResult
import com.fabledt5.domain.repository.ErrorRepository
import javax.inject.Inject

class ValidatePassword @Inject constructor(
    private val errorRepository: ErrorRepository
) {

    operator fun invoke(password: String): ValidationResult {
        if (password.isBlank()) return ValidationResult(
            isSuccessful = false,
            errorMessage = errorRepository.resolveEmptyPassword()
        )
        if (password.length < 8) return ValidationResult(
            isSuccessful = false,
            errorMessage = errorRepository.resolveShortPassword()
        )
        if (password.any { !it.isDigit() }) return ValidationResult(
            isSuccessful = false,
            errorMessage = errorRepository.resolveIncorrectPassword()
        )
        return ValidationResult(isSuccessful = true)
    }

}