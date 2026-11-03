package br.com.denisecastro.safebus.feature.profile.components.header

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.denisecastro.safebus.R
import br.com.denisecastro.safebus.feature.profile.components.avatar.ProfileAvatar
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme

@Composable
fun ProfileHeader(
    name: String,
    email: String,
    isEmailVerified: Boolean,
    modifier: Modifier = Modifier,
    photoUrl: String? = null
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ProfileAvatar(
            photoUrl = photoUrl
        )

        Text(
            text = name.ifBlank {
                stringResource(R.string.profile_default_name)
            },
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
        )

        Text(
            text = email,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Preview(name = "ProfileHeader - Claro", showBackground = true)
@Composable
private fun ProfileHeaderLightPreview() {
    SafeBusTheme(darkTheme = false) {
        Surface {
            ProfileHeader(
                name = stringResource(R.string.personal_data_preview_name),
                email = stringResource(R.string.personal_data_preview_email),
                isEmailVerified = true
            )
        }
    }
}

@Preview(name = "ProfileHeader - Escuro", showBackground = true)
@Composable
private fun ProfileHeaderDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        Surface {
            ProfileHeader(
                name = stringResource(R.string.personal_data_preview_name),
                email = stringResource(R.string.personal_data_preview_email),
                isEmailVerified = true
            )
        }
    }
}