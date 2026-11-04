
package br.com.denisecastro.safebus.feature.profile.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.denisecastro.safebus.R
import br.com.denisecastro.safebus.feature.profile.components.cards.PasswordResetCard
import br.com.denisecastro.safebus.feature.profile.components.security.AuthenticationMethodCard
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme

@Composable
fun SecurityScreen(
    authenticationMethod: String,
    canResetPassword: Boolean,
    onBackClick: () -> Unit,
    onResetPasswordClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.navigationBars),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            androidx.compose.material3.Text(
                text = stringResource(R.string.profile_security),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onBackground
            )

            AuthenticationMethodCard(
                authenticationMethod = authenticationMethod
            )

            if (canResetPassword) {
                PasswordResetCard(
                    onResetPasswordClick = onResetPasswordClick
                )
            }
        }
    }
}

@Preview(name = "Segurança - Claro", showSystemUi = true)
@Composable
private fun SecurityScreenLightPreview() {
    SafeBusTheme(darkTheme = false) {
        SecurityScreen(
            authenticationMethod = stringResource(
                R.string.security_email_password
            ),
            canResetPassword = true,
            onBackClick = {},
            onResetPasswordClick = {}
        )
    }
}

@Preview(name = "Segurança - Escuro", showSystemUi = true)
@Composable
private fun SecurityScreenDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        SecurityScreen(
            authenticationMethod = stringResource(
                R.string.security_email_password
            ),
            canResetPassword = true,
            onBackClick = {},
            onResetPasswordClick = {}
        )
    }
}
