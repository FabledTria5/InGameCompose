package com.fabledt5.domain.use_case.authentication.validation

import com.fabledt5.domain.model.ValidationResult
import com.fabledt5.domain.repository.ErrorRepository
import java.util.regex.Pattern
import javax.inject.Inject

class ValidateEmail @Inject constructor(
    private val errorRepository: ErrorRepository
) {

    private companion object {
        const val EMAIL_PATTERN =
            "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$"
    }

    operator fun invoke(email: String): ValidationResult {
        if (email.isBlank()) return ValidationResult(
            isSuccessful = false,
            errorMessage = errorRepository.resolveEmptyEmail()
        )
        if (!Pattern.matches(
                EMAIL_PATTERN,
                email
            )
        ) return ValidationResult(
            isSuccessful = false,
            errorMessage = errorRepository.resolveIncorrectEmail()
        )
        return ValidationResult(isSuccessful = true)
    }

}