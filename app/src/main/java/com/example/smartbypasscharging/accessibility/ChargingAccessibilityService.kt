package com.example.smartbypasscharging.accessibility

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.provider.Settings
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ChargingAccessibilityService : AccessibilityService() {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    override fun onAccessibilityEvent(event: AccessibilityEvent?) = Unit
    override fun onInterrupt() = Unit

    override fun onServiceConnected() {
        instance = this
        Log.d(TAG, "Accessibility connected")
    }

    override fun onDestroy() {
        if (instance === this) instance = null
        super.onDestroy()
    }

    fun setBypassEnabled(enable: Boolean) = scope.launch {
        Log.d(TAG, "Accessibility action started: target=$enable")
        openChargingSettings()
        val root = waitForNode("Bypass charging")
        val label = root?.let { findNodeByText(it, "Bypass charging") }
        val switch = label?.let { findSwitchNearText(it) }
        if (switch == null) {
            Log.w(TAG, "Bypass charging switch not found")
            return@launch
        }
        if (switch.isChecked != enable) {
            performClick(switch)
            Log.d(TAG, if (enable) "Bypass enabled" else "Bypass disabled")
        } else {
            Log.d(TAG, "Bypass already in target state")
        }
    }

    private fun openChargingSettings() {
        val intents = listOf(
            Intent(Settings.ACTION_BATTERY_SAVER_SETTINGS),
            Intent(Settings.ACTION_BATTERY_SETTINGS),
            Intent(Settings.ACTION_SETTINGS)
        )
        startActivity(intents.first().addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
    }

    suspend fun waitForNode(text: String, attempts: Int = 20): AccessibilityNodeInfo? {
        repeat(attempts) {
            val root = rootInActiveWindow
            if (root != null && findNodeByText(root, text) != null) return root
            delay(500)
        }
        Log.w(TAG, "Settings screen not found or node missing: $text")
        return null
    }

    fun findNodeByText(node: AccessibilityNodeInfo, text: String): AccessibilityNodeInfo? {
        if (node.text?.toString()?.contains(text, ignoreCase = true) == true) return node
        for (i in 0 until node.childCount) node.getChild(i)?.let { child -> findNodeByText(child, text)?.let { return it } }
        return null
    }

    fun findSwitchNearText(label: AccessibilityNodeInfo): AccessibilityNodeInfo? {
        var cursor: AccessibilityNodeInfo? = label
        repeat(4) {
            cursor?.let { node -> findSwitch(node)?.let { return it } }
            cursor = cursor?.parent
        }
        return null
    }

    private fun findSwitch(node: AccessibilityNodeInfo): AccessibilityNodeInfo? {
        val cls = node.className?.toString().orEmpty()
        if (cls.contains("Switch", true) || cls.contains("CheckBox", true)) return node
        for (i in 0 until node.childCount) node.getChild(i)?.let { findSwitch(it)?.let { match -> return match } }
        return null
    }

    fun performClick(node: AccessibilityNodeInfo): Boolean {
        var cursor: AccessibilityNodeInfo? = node
        while (cursor != null) {
            if (cursor!!.isClickable) return cursor!!.performAction(AccessibilityNodeInfo.ACTION_CLICK)
            cursor = cursor!!.parent
        }
        return false
    }

    companion object {
        private const val TAG = "ChargingA11y"
        @Volatile private var instance: ChargingAccessibilityService? = null
        fun requestBypass(enable: Boolean): Boolean = instance?.let { it.setBypassEnabled(enable); true } ?: false
    }
}
