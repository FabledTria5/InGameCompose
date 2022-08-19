package com.fabledt5.domain.use_case.authentication.validation

import com.fabledt5.domain.model.ValidationResult
import com.fabledt5.domain.repository.ErrorRepository
import javax.inject.Inject

class ValidateNickname @Inject constructor(
    private val errorRepository: ErrorRepository
) {

    operator fun invoke(nickname: String): ValidationResult {
        if (nickname.isEmpty()) return ValidationResult(
            isSuccessful = false,
            errorMessage = errorRepository.resolveEmptyNickname()
        )
        return ValidationResult(isSuccessful = true)
    }

}