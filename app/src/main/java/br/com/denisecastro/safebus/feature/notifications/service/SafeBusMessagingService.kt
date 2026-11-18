package br.com.denisecastro.safebus.feature.notifications.service

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import br.com.denisecastro.safebus.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class SafeBusMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        Log.d(TAG, "FCM message received: ${message.messageId}")

        val title = message.notification?.title
            ?: getString(R.string.app_name)

        val body = message.notification?.body ?: return

        val isSecurityAlert = message.data["type"] == "security_alert"

        showNotification(title, body, isSecurityAlert)
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        Log.d(TAG, "FCM token updated")
    }

    private fun showNotification(
        title: String,
        body: String,
        isSecurityAlert: Boolean
    ) {
        val notificationManager = getSystemService(
            NotificationManager::class.java
        )

        val channelId = if (isSecurityAlert) {
            SECURITY_CHANNEL_ID
        } else {
            GENERAL_CHANNEL_ID
        }

        val channelName = if (isSecurityAlert) {
            getString(R.string.notification_security_channel)
        } else {
            getString(R.string.notification_general_channel)
        }

        val importance = if (isSecurityAlert) {
            NotificationManager.IMPORTANCE_HIGH
        } else {
            NotificationManager.IMPORTANCE_DEFAULT
        }

        val priority = if (isSecurityAlert) {
            NotificationCompat.PRIORITY_HIGH
        } else {
            NotificationCompat.PRIORITY_DEFAULT
        }

        val channel = NotificationChannel(
            channelId,
            channelName,
            importance
        )

        notificationManager.createNotificationChannel(channel)

        if (
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        val notification = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(title)
            .setContentText(body)
            .setStyle(NotificationCompat.BigTextStyle().bigText(body))
            .setPriority(priority)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(
            System.currentTimeMillis().toInt(),
            notification
        )
    }

    companion object {
        private const val TAG = "SafeBusFCM"

        private const val SECURITY_CHANNEL_ID = "safebus_security"
        private const val GENERAL_CHANNEL_ID = "safebus_general"
    }
}
