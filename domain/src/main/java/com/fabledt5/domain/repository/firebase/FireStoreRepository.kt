package com.fabledt5.domain.repository.firebase

import com.fabledt5.domain.model.Resource

interface FireStoreRepository {

    suspend fun createUser(
        userEmail: String,
        userNickname: String,
        userUId: String
    ): Resource<Boolean>

}