package com.fabledt5.ingamecompose

import android.app.Application
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class InGameApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Firebase.initialize(applicationContext)
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

}