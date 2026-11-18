package br.com.denisecastro.safebus.feature.notifications.channel

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import br.com.denisecastro.safebus.R

object NotificationChannels {

    const val SECURITY_CHANNEL_ID = "safebus_security"
    const val GENERAL_CHANNEL_ID = "safebus_general"

    fun create(context: Context) {
        val manager = context.getSystemService(
            NotificationManager::class.java
        )

        val securityChannel = NotificationChannel(
            SECURITY_CHANNEL_ID,
            context.getString(R.string.notification_security_channel),
            NotificationManager.IMPORTANCE_HIGH
        )

        val generalChannel = NotificationChannel(
            GENERAL_CHANNEL_ID,
            context.getString(R.string.notification_general_channel),
            NotificationManager.IMPORTANCE_DEFAULT
        )

        manager.createNotificationChannels(
            listOf(securityChannel, generalChannel)
        )
    }
}
