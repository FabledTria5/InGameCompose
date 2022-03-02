package com.fabledt5.preferences.di

import com.fabledt5.preferences.AppPreferences
import com.fabledt5.preferences.AppPreferencesImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface BindingModule {

    @Binds
    fun bindPreferences(appPreferencesImpl: AppPreferencesImpl): AppPreferences

}