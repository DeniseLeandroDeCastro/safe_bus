
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

        showNotification(title, body)
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        Log.d(TAG, "FCM token updated")
    }

    private fun showNotification(title: String, body: String) {
        val notificationManager = getSystemService(
            NotificationManager::class.java
        )

        val channel = NotificationChannel(
            CHANNEL_ID,
            getString(R.string.notification_channel_name),
            NotificationManager.IMPORTANCE_DEFAULT
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

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(title)
            .setContentText(body)
            .setStyle(NotificationCompat.BigTextStyle().bigText(body))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(
            System.currentTimeMillis().toInt(),
            notification
        )
    }

    companion object {
        private const val TAG = "SafeBusFCM"
        private const val CHANNEL_ID = "safebus_notifications"
    }
}
