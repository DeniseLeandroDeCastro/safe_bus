package br.com.denisecastro.safebus.feature.notifications.screens

import android.app.NotificationManager
import android.content.Intent
import android.provider.Settings
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.NotificationsNone
import androidx.compose.material.icons.outlined.Shield
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LifecycleResumeEffect
import br.com.denisecastro.safebus.R
import br.com.denisecastro.safebus.feature.notifications.channel.NotificationChannels
import br.com.denisecastro.safebus.feature.notifications.components.row.NotificationPreferenceRow
import br.com.denisecastro.safebus.feature.profile.components.cards.SectionCard
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme

@Composable
fun NotificationsScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val isPreview = LocalInspectionMode.current

    val notificationManager = remember(context, isPreview) {
        if (isPreview) {
            null
        } else {
            context.getSystemService(NotificationManager::class.java)
        }
    }

    var securityEnabled by remember { mutableStateOf(false) }
    var generalEnabled by remember { mutableStateOf(false) }

    if (!isPreview) {
        LifecycleResumeEffect(Unit) {
            val appEnabled =
                notificationManager?.areNotificationsEnabled() == true

            securityEnabled = appEnabled &&
                    notificationManager?.getNotificationChannel(
                        NotificationChannels.SECURITY_CHANNEL_ID
                    )?.importance?.let {
                        it != NotificationManager.IMPORTANCE_NONE
                    } == true

            generalEnabled = appEnabled &&
                    notificationManager?.getNotificationChannel(
                        NotificationChannels.GENERAL_CHANNEL_ID
                    )?.importance?.let {
                        it != NotificationManager.IMPORTANCE_NONE
                    } == true

            onPauseOrDispose { }
        }
    }

    fun openChannelSettings(channelId: String) {
        val intent = Intent(
            Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS
        ).apply {
            putExtra(
                Settings.EXTRA_APP_PACKAGE,
                context.packageName
            )
            putExtra(
                Settings.EXTRA_CHANNEL_ID,
                channelId
            )
        }

        context.startActivity(intent)
    }

    Surface(
        modifier = modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.navigationBars),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(
                    horizontal = 20.dp,
                    vertical = 24.dp
                ),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            IconButton(
                onClick = onBackClick
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.back)
                )
            }

            Text(
                text = stringResource(R.string.profile_notifications),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                text = stringResource(R.string.notifications_description),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            SectionCard(
                titleRes = R.string.notifications_preferences_title
            ) {
                NotificationPreferenceRow(
                    titleRes = R.string.notification_security_channel,
                    descriptionRes = R.string.notifications_security_description,
                    icon = Icons.Outlined.Shield,
                    isEnabled = securityEnabled,
                    onClick = {
                        openChannelSettings(
                            NotificationChannels.SECURITY_CHANNEL_ID
                        )
                    }
                )

                HorizontalDivider()

                NotificationPreferenceRow(
                    titleRes = R.string.notification_general_channel,
                    descriptionRes = R.string.notifications_general_description,
                    icon = Icons.Outlined.NotificationsNone,
                    isEnabled = generalEnabled,
                    onClick = {
                        openChannelSettings(
                            NotificationChannels.GENERAL_CHANNEL_ID
                        )
                    }
                )
            }
        }
    }
}

@Preview(name = "Notificações - Claro", showSystemUi = true)
@Composable
private fun NotificationsScreenLightPreview() {
    SafeBusTheme(darkTheme = false) {
        NotificationsScreen(
            onBackClick = {}
        )
    }
}

@Preview(name = "Notificações - Escuro", showSystemUi = true)
@Composable
private fun NotificationsScreenDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        NotificationsScreen(
            onBackClick = {}
        )
    }
}
