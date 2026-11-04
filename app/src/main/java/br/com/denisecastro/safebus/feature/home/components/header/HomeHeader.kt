package br.com.denisecastro.safebus.feature.home.components.header

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.denisecastro.safebus.R
import br.com.denisecastro.safebus.feature.profile.components.avatar.ProfileAvatar
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme

@Composable
fun HomeHeader(
    userName: String,
    photoUrl: String?,
    onNotificationsClick: () -> Unit,
    onProfileClick: () -> Unit,
    onLogoutClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = if (userName.isBlank()) {
                    stringResource(R.string.home_greeting)
                } else {
                    stringResource(R.string.home_greeting_name, userName)
                },
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                text = stringResource(R.string.home_subtitle),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        IconButton(onClick = onNotificationsClick) {
            Icon(
                imageVector = Icons.Filled.NotificationsNone,
                contentDescription = stringResource(R.string.home_notifications),
                tint = MaterialTheme.colorScheme.onBackground
            )
        }

        IconButton(onClick = onProfileClick) {
            ProfileAvatar(
                photoUrl = photoUrl,
                size = 44.dp
            )
        }

        IconButton(onClick = onLogoutClick) {
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.Logout,
                contentDescription = stringResource(R.string.profile_logout),
                tint = MaterialTheme.colorScheme.error
            )
        }
    }
}

@Preview(name = "HomeHeader - Claro", showBackground = true)
@Composable
private fun HomeHeaderLightPreview() {
    SafeBusTheme(darkTheme = false) {
        Surface {
            HomeHeader(
                userName = stringResource(R.string.personal_data_preview_name),
                photoUrl = null,
                onNotificationsClick = {},
                onProfileClick = {},
                onLogoutClick = {}
            )
        }
    }
}

@Preview(name = "HomeHeader - Escuro", showBackground = true)
@Composable
private fun HomeHeaderDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        Surface {
            HomeHeader(
                userName = stringResource(R.string.personal_data_preview_name),
                photoUrl = null,
                onNotificationsClick = {},
                onProfileClick = {},
                onLogoutClick = {}
            )
        }
    }
}


