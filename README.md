# Smart Bypass Charging

Android app prototype in Kotlin that monitors battery state and uses an `AccessibilityService` to toggle a manufacturer-provided **Bypass charging** switch when charging and the configured threshold is reached.

## Important compatibility note

Android does not expose a standard API for vendor-specific bypass charging. This project uses Accessibility automation against Settings UI text. Before relying on it, manually verify:

1. The bypass charging switch is reachable through Accessibility.
2. Your tablet allows Accessibility services to interact with the charging settings page.
3. The label `Bypass charging` is stable for your firmware and language.

## Features

- Kotlin + MVVM + Jetpack Compose.
- Hilt dependency injection.
- DataStore Preferences threshold persistence.
- Battery broadcast monitoring with Flow.
- Foreground service named **Smart Charging Service**.
- Periodic WorkManager safety check.
- Boot receiver to restart monitoring after reboot.
- Accessibility node traversal helpers: `findNodeByText`, `findSwitchNearText`, `performClick`, and `waitForNode`.

## Setup

1. Open this repository in Android Studio.
2. Sync Gradle.
3. Install the app on an Android 11+ device.
4. Grant notification permission on Android 13+ if prompted.
5. Open **Accessibility Settings** from the dashboard and enable **Smart Bypass Charging**.
6. Use **Open Charging Settings** and confirm the switch label is exactly `Bypass charging`.
7. Return to the app, choose a threshold, and tap **Enable Monitoring**.

## Troubleshooting

- **Switch not found**: firmware text may differ; update `ChargingAccessibilityService` with the device-specific label.
- **Settings screen not found**: vendor charging controls may live under a proprietary activity; adjust `openChargingSettings()`.
- **No background monitoring**: confirm foreground notification is visible and battery optimization is not killing the app.
- **Reboot did not restart**: open the app once after install and ensure the device allows boot receivers for user-installed apps.
