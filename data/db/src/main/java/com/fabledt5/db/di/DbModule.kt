package com.fabledt5.db.di

import android.content.Context
import androidx.room.Room
import com.fabledt5.db.GamesDataBase
import com.fabledt5.db.dao.HotGamesDao
import com.fabledt5.db.dao.PlatformsDao
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
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideHotGamesDao(gamesDataBase: GamesDataBase): HotGamesDao = gamesDataBase.hotGamesDao()

    @Singleton
    @Provides
    fun providePlatformsDao(gamesDataBase: GamesDataBase): PlatformsDao =
        gamesDataBase.platformsDao()

}