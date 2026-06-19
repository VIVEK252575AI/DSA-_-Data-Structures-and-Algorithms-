package com.example.smartbypasscharging.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.smartbypasscharging.accessibility.ChargingAccessibilityService
import com.example.smartbypasscharging.battery.BatteryMonitorManager
import com.example.smartbypasscharging.data.SettingsRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.first

@HiltWorker
class ChargingWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val battery: BatteryMonitorManager,
    private val settings: SettingsRepository
) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        if (!settings.monitoringEnabled.first()) return Result.success()
        val state = battery.currentState() ?: return Result.retry()
        val threshold = settings.threshold.first()
        ChargingAccessibilityService.requestBypass(state.isCharging && state.percentage >= threshold)
        return Result.success()
    }
}
