package com.fabledt5.remote.di

import com.fabledt5.remote.parser.ReviewsParser
import com.fabledt5.remote.parser.ReviewsParserImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface BindingModule {

    @Binds
    fun bindParser(reviewsParserImpl: ReviewsParserImpl): ReviewsParser

}