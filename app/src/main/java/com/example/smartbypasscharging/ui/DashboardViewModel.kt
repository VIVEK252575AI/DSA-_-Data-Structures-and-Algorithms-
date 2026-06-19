package com.example.smartbypasscharging.ui

import android.app.Application
import android.content.Intent
import android.provider.Settings
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartbypasscharging.battery.BatteryMonitorManager
import com.example.smartbypasscharging.data.SettingsRepository
import com.example.smartbypasscharging.service.SmartChargingService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DashboardState(val battery: Int = 0, val charging: Boolean = false, val threshold: Int = 80, val monitoring: Boolean = false)

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val app: Application,
    private val batteryManager: BatteryMonitorManager,
    private val repo: SettingsRepository
) : AndroidViewModel(app) {
    val state = combine(batteryManager.updates(), repo.threshold, repo.monitoringEnabled) { b, threshold, monitoring ->
        DashboardState(b.percentage, b.isCharging, threshold, monitoring)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), DashboardState())

    fun setThreshold(value: Int) = viewModelScope.launch { repo.setThreshold(value) }
    fun setMonitoring(enabled: Boolean) = viewModelScope.launch {
        repo.setMonitoring(enabled)
        val intent = Intent(app, SmartChargingService::class.java)
        if (enabled) ContextCompat.startForegroundService(app, intent) else app.stopService(intent)
    }
    fun openAccessibilitySettings() = app.startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
    fun openChargingSettings() = app.startActivity(Intent(Settings.ACTION_BATTERY_SETTINGS).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
}
