package br.com.denisecastro.safebus.feature.profile.components.header

import android.view.Surface
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.denisecastro.safebus.feature.profile.model.ProfileUiState
import br.com.denisecastro.safebus.feature.profile.screens.ProfileScreen
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme

@Composable
fun ProfileHeader(
    name: String,
    email: String,
    isEmailVerified: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            imageVector = Icons.Outlined.AccountCircle,
            contentDescription = null,
            modifier = Modifier.size(88.dp),
            tint = MaterialTheme.colorScheme.primary
        )

        Text(
            text = name.ifBlank { "Usuário SafeBus" },
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


@Preview(
    name = "Perfil - Claro",
    showSystemUi = true
)
@Composable
private fun ProfileScreenLightPreview() {
    SafeBusTheme(darkTheme = false) {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
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

@Preview(
    name = "Perfil - Escuro",
    showSystemUi = true
)
@Composable
private fun ProfileScreenDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
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



