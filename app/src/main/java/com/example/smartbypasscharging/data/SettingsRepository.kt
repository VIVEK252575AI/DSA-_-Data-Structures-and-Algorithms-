package com.example.smartbypasscharging.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore("smart_bypass_settings")

@Singleton
class SettingsRepository @Inject constructor(@ApplicationContext private val context: Context) {
    private val thresholdKey = intPreferencesKey("threshold")
    private val monitoringKey = booleanPreferencesKey("monitoring_enabled")

    val threshold: Flow<Int> = context.dataStore.data.map { it[thresholdKey] ?: 80 }
    val monitoringEnabled: Flow<Boolean> = context.dataStore.data.map { it[monitoringKey] ?: false }

    suspend fun setThreshold(value: Int) = context.dataStore.edit { it[thresholdKey] = value }
    suspend fun setMonitoring(enabled: Boolean) = context.dataStore.edit { it[monitoringKey] = enabled }
}
