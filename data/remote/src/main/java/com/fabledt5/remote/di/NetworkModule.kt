package com.fabledt5.remote.di

import com.fabledt5.remote.BuildConfig
import com.fabledt5.remote.api.ApiService
import com.fabledt5.remote.api.InGameService
import com.fabledt5.remote.utils.ApiInterceptor
import com.fabledt5.remote.utils.Constants.API_BASE_URL
import com.fabledt5.remote.utils.Constants.INGAME_SERVER_BASE_URL
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Suppress("JSON_FORMAT_REDUNDANT")
@ExperimentalSerializationApi
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideApiClient(): OkHttpClient = if (BuildConfig.DEBUG) {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor(ApiInterceptor)
            .build()
    } else {
        OkHttpClient.Builder().build()
    }

    @Singleton
    @Provides
    fun provideJson(): Converter.Factory = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
    }.asConverterFactory("application/json".toMediaType())

    @Singleton
    @Provides
    @Named("RemoteServer")
    fun provideRemoteServerRetrofit(
        okHttpClient: OkHttpClient,
        jsonConverter: Converter.Factory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(INGAME_SERVER_BASE_URL)
        .addConverterFactory(jsonConverter)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    @Named("Api")
    fun provideApiRetrofit(okHttpClient: OkHttpClient, jsonConverter: Converter.Factory): Retrofit =
        Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(jsonConverter)
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    fun provideRemoteServerService(@Named("RemoteServer") retrofit: Retrofit): InGameService =
        retrofit.create(InGameService::class.java)

    @Singleton
    @Provides
    fun provideGamesService(@Named("Api") retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

}