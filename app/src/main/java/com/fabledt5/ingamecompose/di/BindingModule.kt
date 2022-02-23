package com.fabledt5.ingamecompose.di

import com.fabledt5.domain.repository.AuthRepository
import com.fabledt5.domain.repository.FireStoreRepository
import com.fabledt5.domain.repository.GamesListRepository
import com.fabledt5.repository.AuthRepositoryImpl
import com.fabledt5.repository.FireStoreRepositoryImpl
import com.fabledt5.repository.GamesListRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface BindingModule {

    @Binds
    fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    fun bindGamesRepository(gamesListRepositoryImpl: GamesListRepositoryImpl): GamesListRepository

    @Binds
    fun bindFirestoreRepository(fireStoreRepositoryImpl: FireStoreRepositoryImpl): FireStoreRepository

}