package com.fabledt5.ingamecompose.di

import com.fabledt5.domain.repository.*
import com.fabledt5.repository.*
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

    @Binds
    fun bindPreferencesRepository(preferencesRepositoryImpl: PreferencesRepositoryImpl): PreferencesRepository

    @Binds
    fun bindGameRepository(gameRepositoryImpl: GameRepositoryImpl): GameRepository

}