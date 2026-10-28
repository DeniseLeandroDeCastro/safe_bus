package br.com.denisecastro.safebus.feature.profile.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.NotificationsNone
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.denisecastro.safebus.R
import br.com.denisecastro.safebus.feature.profile.components.cards.ProfileSectionCard
import br.com.denisecastro.safebus.feature.profile.components.header.ProfileHeader
import br.com.denisecastro.safebus.feature.profile.components.optionrow.ProfileOptionRow
import br.com.denisecastro.safebus.feature.profile.model.ProfileUiState
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme

@Composable
fun ProfileScreen(
    uiState: ProfileUiState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.navigationBars)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = stringResource(R.string.profile_title),
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onBackground
        )

        ProfileHeader(
            name = uiState.name,
            email = uiState.email,
            isEmailVerified = uiState.isEmailVerified
        )

        ProfileSectionCard(
            titleRes = R.string.profile_account_section
        ) {
            ProfileOptionRow(
                titleRes = R.string.profile_personal_data,
                descriptionRes = R.string.profile_personal_data_description,
                icon = Icons.Outlined.PersonOutline
            )

            HorizontalDivider()

            ProfileOptionRow(
                titleRes = R.string.profile_security,
                descriptionRes = R.string.profile_security_description,
                icon = Icons.Outlined.Lock
            )
        }

        ProfileSectionCard(
            titleRes = R.string.profile_preferences_section
        ) {
            ProfileOptionRow(
                titleRes = R.string.profile_notifications,
                descriptionRes = R.string.profile_notifications_description,
                icon = Icons.Outlined.NotificationsNone
            )

            HorizontalDivider()

            ProfileOptionRow(
                titleRes = R.string.profile_appearance,
                descriptionRes = R.string.profile_appearance_description,
                icon = Icons.Outlined.DarkMode
            )
        }

        ProfileSectionCard(
            titleRes = R.string.profile_about_section
        ) {
            ProfileOptionRow(
                titleRes = R.string.profile_version,
                descriptionRes = null,
                icon = Icons.Outlined.Info
            )

            HorizontalDivider()

            ProfileOptionRow(
                titleRes = R.string.profile_terms,
                descriptionRes = R.string.profile_terms_description,
                icon = Icons.Outlined.Description
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Preview(name = "Perfil - Claro", showBackground = true)
@Composable
private fun ProfileScreenLightPreview() {
    SafeBusTheme(darkTheme = false) {
        Surface {
            ProfileScreen(
                uiState = ProfileUiState(
                    name = "Ana Silva",
                    email = "ana@email.com",
                    isEmailVerified = true
                )
            )
        }
    }
}

@Preview(name = "Perfil - Escuro", showBackground = true)
@Composable
private fun ProfileScreenDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        Surface {
            ProfileScreen(
                uiState = ProfileUiState(
                    name = "Ana Silva",
                    email = "ana@email.com",
                    isEmailVerified = true
                )
            )
        }
    }
}
