package com.fabledt5.domain.use_case.authentication

import com.fabledt5.domain.model.Resource
import com.fabledt5.domain.repository.firebase.AuthRepository
import com.fabledt5.domain.repository.firebase.FireStoreRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ExperimentalCoroutinesApi
class RegisterUser @Inject constructor(
    private val authRepository: AuthRepository,
    private val fireStoreRepository: FireStoreRepository
) {
    operator fun invoke(email: String, password: String, nickName: String) = flow {
        emit(Resource.Loading)
        when (val signUpResult = authRepository.signUpFirebase(email, password)) {
            is Resource.Error -> emit(signUpResult)
            is Resource.Success -> {
                val uId = signUpResult.data
                uId?.let {
                    fireStoreRepository.createUser(
                        userEmail = email,
                        userNickname = nickName,
                        userUId = uId
                    )
                }
            }
            else -> Unit
        }
    }
}