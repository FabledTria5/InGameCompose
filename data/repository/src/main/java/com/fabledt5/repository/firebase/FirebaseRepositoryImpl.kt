package com.fabledt5.repository.firebase

import com.fabledt5.domain.repository.firebase.FirebaseRepository
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseRepositoryImpl @Inject constructor(private val database: FirebaseDatabase) :
    FirebaseRepository {



}