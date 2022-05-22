package com.fabledt5.ingamecompose.di

import com.fabledt5.domain.repository.*
import com.fabledt5.repository.firebase.AuthRepositoryImpl
import com.fabledt5.repository.firebase.FireStoreRepositoryImpl
import com.fabledt5.repository.local.PreferencesRepositoryImpl
import com.fabledt5.repository.remote.FiltersRepositoryImpl
import com.fabledt5.repository.remote.GameRepositoryImpl
import com.fabledt5.repository.remote.GamesListRepositoryImpl
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

    @Binds
    fun bindFiltersRepository(filtersRepositoryImpl: FiltersRepositoryImpl): FiltersRepository

}