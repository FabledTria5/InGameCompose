package com.fabledt5.domain.use_case.authentication

import com.fabledt5.domain.model.Resource
import com.fabledt5.domain.repository.AuthRepository
import com.fabledt5.domain.repository.FireStoreRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@ExperimentalCoroutinesApi
class RegisterUser @Inject constructor(
    private val authRepository: AuthRepository,
    private val fireStoreRepository: FireStoreRepository
) {
    operator fun invoke(email: String, password: String, nickName: String) =
        authRepository.signUpFirebase(email, password).flatMapLatest { resource ->
            when (resource) {
                is Resource.Error -> flowOf(Resource.Error(message = "User was not created"))
                Resource.Loading -> flowOf(Resource.Loading)
                is Resource.Success -> {
                    resource.data?.let { uid ->
                        fireStoreRepository.createUser(
                            userEmail = email,
                            userNickname = nickName,
                            userUId = uid
                        )
                    } ?: flowOf(Resource.Error(message = "User was not created"))
                }
                else -> flowOf(Resource.Idle)
            }
        }
}