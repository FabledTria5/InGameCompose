package com.fabledt5.repository.firebase

import com.fabledt5.domain.repository.FirebaseRepository
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject

class FirebaseRepositoryImpl @Inject constructor(private val database: FirebaseDatabase) :
    FirebaseRepository {



}