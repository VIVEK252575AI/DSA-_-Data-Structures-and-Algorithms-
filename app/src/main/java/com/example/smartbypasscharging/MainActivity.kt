package com.example.smartbypasscharging

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.smartbypasscharging.ui.DashboardViewModel
import com.example.smartbypasscharging.worker.ChargingWorker
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: DashboardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        scheduleWorker()
        setContent {
            val state by viewModel.state.collectAsState()
            MaterialTheme {
                Surface(Modifier.fillMaxSize()) {
                    Column(Modifier.padding(24.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Text("Smart Bypass Charging", style = MaterialTheme.typography.headlineSmall)
                        Text("Battery Percentage: ${state.battery}%")
                        Text("Charging Status: ${if (state.charging) "Connected" else "Disconnected"}")
                        Text("Bypass Status: ${if (state.charging && state.battery >= state.threshold) "Should be ON" else "Should be OFF"}")
                        Text("Accessibility Status: Check Android accessibility settings")
                        Text("Service Status: ${if (state.monitoring) "Enabled" else "Disabled"}")
                        Text("Threshold: ${state.threshold}%")
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            listOf(60, 70, 80, 90).forEach { value ->
                                Row { RadioButton(selected = state.threshold == value, onClick = { viewModel.setThreshold(value) }); Text("$value%") }
                            }
                        }
                        Spacer(Modifier.height(8.dp))
                        Button(onClick = { viewModel.setMonitoring(true) }) { Text("Enable Monitoring") }
                        Button(onClick = { viewModel.setMonitoring(false) }) { Text("Disable Monitoring") }
                        Button(onClick = viewModel::openAccessibilitySettings) { Text("Open Accessibility Settings") }
                        Button(onClick = viewModel::openChargingSettings) { Text("Open Charging Settings") }
                    }
                }
            }
        }
    }

    private fun scheduleWorker() {
        val request = PeriodicWorkRequestBuilder<ChargingWorker>(15, TimeUnit.MINUTES).build()
        WorkManager.getInstance(this).enqueueUniquePeriodicWork("charging_worker", ExistingPeriodicWorkPolicy.UPDATE, request)
    }
}
