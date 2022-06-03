package com.fabledt5.domain.repository.firebase

import com.fabledt5.domain.model.Resource
import kotlinx.coroutines.flow.Flow

interface FireStoreRepository {

    fun createUser(
        userEmail: String,
        userNickname: String,
        userUId: String
    ): Flow<Resource<Boolean>>

}