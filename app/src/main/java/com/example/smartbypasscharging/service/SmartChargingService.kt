package com.example.smartbypasscharging.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.smartbypasscharging.R
import com.example.smartbypasscharging.accessibility.ChargingAccessibilityService
import com.example.smartbypasscharging.battery.BatteryMonitorManager
import com.example.smartbypasscharging.data.SettingsRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SmartChargingService : Service() {
    @Inject lateinit var battery: BatteryMonitorManager
    @Inject lateinit var settings: SettingsRepository
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()
        createChannel()
        startForeground(1, NotificationCompat.Builder(this, CHANNEL)
            .setSmallIcon(android.R.drawable.ic_lock_idle_charging)
            .setContentTitle("Smart Charging Service")
            .setContentText("Monitoring charging status")
            .setOngoing(true).build())
        scope.launch {
            combine(battery.updates(), settings.threshold, settings.monitoringEnabled) { b, threshold, enabled -> Triple(b, threshold, enabled) }
                .collect { (b, threshold, enabled) ->
                    if (enabled) ChargingAccessibilityService.requestBypass(b.isCharging && b.percentage >= threshold)
                }
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int) = START_STICKY
    override fun onBind(intent: Intent?): IBinder? = null
    override fun onDestroy() { scope.cancel(); super.onDestroy() }

    private fun createChannel() {
        getSystemService(NotificationManager::class.java).createNotificationChannel(
            NotificationChannel(CHANNEL, "Smart Charging Service", NotificationManager.IMPORTANCE_LOW))
    }
    companion object { const val CHANNEL = "smart_charging" }
}
