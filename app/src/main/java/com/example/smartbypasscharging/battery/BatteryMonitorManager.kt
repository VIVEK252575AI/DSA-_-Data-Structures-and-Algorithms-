package com.example.smartbypasscharging.battery

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject
import javax.inject.Singleton

data class BatteryState(val percentage: Int = 0, val isCharging: Boolean = false)

@Singleton
class BatteryMonitorManager @Inject constructor(@ApplicationContext private val context: Context) {
    fun updates(): Flow<BatteryState> = callbackFlow {
        val receiver = object : BroadcastReceiver() {
            override fun onReceive(ctx: Context, intent: Intent) {
                val state = intent.toBatteryState()
                Log.d("BatteryMonitor", "Battery changed: $state")
                trySend(state)
            }
        }
        context.registerReceiver(receiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
        currentState()?.let { trySend(it) }
        awaitClose { context.unregisterReceiver(receiver) }
    }

    fun currentState(): BatteryState? = context.registerReceiver(null, IntentFilter(Intent.ACTION_BATTERY_CHANGED))?.toBatteryState()

    private fun Intent.toBatteryState(): BatteryState {
        val level = getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
        val scale = getIntExtra(BatteryManager.EXTRA_SCALE, -1)
        val pct = if (level >= 0 && scale > 0) (level * 100) / scale else 0
        val status = getIntExtra(BatteryManager.EXTRA_STATUS, -1)
        val plugged = getIntExtra(BatteryManager.EXTRA_PLUGGED, 0) != 0
        return BatteryState(pct, plugged || status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL)
    }
}
