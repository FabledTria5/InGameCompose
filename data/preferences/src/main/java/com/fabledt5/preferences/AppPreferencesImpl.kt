package com.fabledt5.preferences

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import com.fabledt5.preferences.utils.Constants
import com.fabledt5.preferences.utils.datastore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class AppPreferencesImpl @Inject constructor(@ApplicationContext context: Context) :
    AppPreferences {

    private object PreferencesKeys {
        val favoritePlatform = intPreferencesKey(name = Constants.FAVORITE_PLATFORM_KEY)
    }

    private val datastore = context.datastore

    override suspend fun persistFavoritePlatform(platformId: Int) {
        datastore.edit { preference ->
            preference[PreferencesKeys.favoritePlatform] = platformId
        }
    }

    override val readFavoritePlatform: Flow<Int> = datastore.data
        .catch { exception ->
            if (exception is IOException) emit(emptyPreferences()) else throw exception
        }
        .map { preference ->
            preference[PreferencesKeys.favoritePlatform] ?: 0
        }

}