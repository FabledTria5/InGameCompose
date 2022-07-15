package com.fabledt5.repository.firebase

import com.fabledt5.domain.model.Resource
import com.fabledt5.domain.repository.ErrorRepository
import com.fabledt5.domain.repository.firebase.FireStoreRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FireStoreRepositoryImpl @Inject constructor(
    private val firebase: FirebaseFirestore,
    private val errorRepository: ErrorRepository
) : FireStoreRepository {

    override suspend fun createUser(
        userEmail: String,
        userNickname: String,
        userUId: String
    ) = try {
        val documentReference = firebase.collection("users").document(userUId)
        val userData = mapOf<String, Any>("email" to userEmail, "nickname" to userNickname)
        documentReference.set(userData).await()
        Resource.Success(data = true)
    } catch (exception: FirebaseFirestoreException) {
        Timber.e(exception)
        val error = errorRepository.resolveRemoteUserDataError(exception)
        Resource.Error(error)
    }
}