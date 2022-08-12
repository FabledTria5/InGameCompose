package com.fabledt5.ingamecompose

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidApp
class InGameApplication : Application() {

    @Inject
    @Named("Notification Channel Id")
    lateinit var notificationChannelId: String

    override fun onCreate() {
        super.onCreate()

        Firebase.initialize(applicationContext)

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) createNotificationChannel()
    }

    private fun createNotificationChannel() {
        val channelName = getString(R.string.notification_channel_id)
        val descriptionText = getString(R.string.notification_channel_Description)
        val importance = NotificationManager.IMPORTANCE_HIGH

        val channel = NotificationChannel(channelName, descriptionText, importance)
        val notificationManager = getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)
    }

}