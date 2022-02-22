package com.fabledt5.repository

import com.fabledt5.domain.model.Resource
import com.fabledt5.domain.repository.FireStoreRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FireStoreRepositoryImpl @Inject constructor(private val firebase: FirebaseFirestore) :
    FireStoreRepository {

    override fun createUser(
        userEmail: String,
        userNickname: String,
        userUId: String
    ) = flow {
        try {
            val documentReference = firebase.collection("users").document(userUId)
            val userData = mapOf<String, Any>("email" to userEmail, "nickname" to userNickname)
            documentReference.set(userData).await()
            emit(Resource.Success(data = true))
        } catch (e: Exception) {
            emit(Resource.Error(message = "Can not save user data"))
        }
    }

}