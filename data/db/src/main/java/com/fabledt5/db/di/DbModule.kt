package com.fabledt5.db.di

import android.content.Context
import androidx.room.Room
import com.fabledt5.db.GamesDataBase
import com.fabledt5.db.dao.FiltersDao
import com.fabledt5.db.dao.GamesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Singleton
    @Provides
    fun provideGamesDatabase(@ApplicationContext context: Context): GamesDataBase = Room
        .databaseBuilder(context, GamesDataBase::class.java, "games_database")
        .createFromAsset(databaseFilePath = "database/filters.db")
        .build()

    @Singleton
    @Provides
    fun provideGamesDao(gamesDataBase: GamesDataBase): GamesDao = gamesDataBase.gamesDao()

    @Singleton
    @Provides
    fun provideFiltersDao(gamesDataBase: GamesDataBase): FiltersDao =
        gamesDataBase.filtersDao()

}