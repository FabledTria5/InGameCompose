package com.fabledt5.preferences.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.fabledt5.preferences.utils.Constants.USER_DATASTORE_PREFERENCE_NAME

val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = USER_DATASTORE_PREFERENCE_NAME)