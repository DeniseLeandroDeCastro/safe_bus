package br.com.denisecastro.safebus.feature.notifications.components.row

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.NotificationsNone
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.denisecastro.safebus.R
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme

@Composable
fun NotificationPreferenceRow(
    @StringRes titleRes: Int,
    @StringRes descriptionRes: Int,
    icon: ImageVector,
    isEnabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            shape = CircleShape,
            color = MaterialTheme.colorScheme.primaryContainer
        ) {
            Box(
                modifier = Modifier.size(52.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = stringResource(titleRes),
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = stringResource(descriptionRes),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = stringResource(
                    if (isEnabled) {
                        R.string.notifications_enabled
                    } else {
                        R.string.notifications_disabled
                    }
                ),
                style = MaterialTheme.typography.labelMedium,
                color = if (isEnabled) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.onSurfaceVariant
                }
            )
        }
    }
}

@Preview(name = "Preferência - Claro", showBackground = true)
@Composable
private fun NotificationPreferenceRowLightPreview() {
    SafeBusTheme(darkTheme = false) {
        NotificationPreferenceRow(
            titleRes = R.string.notification_security_channel,
            descriptionRes = R.string.notifications_security_description,
            icon = Icons.Outlined.NotificationsNone,
            isEnabled = true,
            onClick = {}
        )
    }
}

@Preview(name = "Preferência - Escuro", showBackground = true)
@Composable
private fun NotificationPreferenceRowDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        NotificationPreferenceRow(
            titleRes = R.string.notification_security_channel,
            descriptionRes = R.string.notifications_security_description,
            icon = Icons.Outlined.NotificationsNone,
            isEnabled = false,
            onClick = {}
        )
    }
}
