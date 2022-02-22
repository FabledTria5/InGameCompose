package com.fabledt5.remote.utils

import com.fabledt5.remote.BuildConfig
import okhttp3.Interceptor

object ApiInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain) = chain.proceed(
        chain.request().newBuilder().url(
            chain.request().url.newBuilder().addQueryParameter("key", BuildConfig.APIKEY).build()
        ).build()
    )

}