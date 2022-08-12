package com.fabledt5.ingamecompose.di

import android.content.Context
import com.fabledt5.ingamecompose.R
import com.fabledt5.navigation.NavigationManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideNavigationManager(): NavigationManager = NavigationManager()

    @Singleton
    @Provides
    @Named("Notification Channel Id")
    fun provideNotificationChannelId(@ApplicationContext context: Context): String =
        context.getString(R.string.notification_channel_id)

}