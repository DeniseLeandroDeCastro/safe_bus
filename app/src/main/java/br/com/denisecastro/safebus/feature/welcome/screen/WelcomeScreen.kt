package br.com.denisecastro.safebus.feature.welcome.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.denisecastro.safebus.feature.welcome.components.header.WelcomeHeader
import br.com.denisecastro.safebus.feature.welcome.components.illustrations.WelcomeIllustration
import br.com.denisecastro.safebus.ui.components.buttons.primary.PrimaryButton
import br.com.denisecastro.safebus.ui.components.buttons.secondary.SecondaryButton
import br.com.denisecastro.safebus.ui.theme.DarkBackground
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme

@Composable
fun WelcomeScreen(
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 24.dp,
                    end = 24.dp,
                    top = 48.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            WelcomeHeader()

            WelcomeIllustration(
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.background,
            shape = androidx.compose.foundation.shape.RoundedCornerShape(
                topStart = 28.dp,
                topEnd = 28.dp
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .windowInsetsPadding(WindowInsets.navigationBars)
                    .padding(
                        horizontal = 24.dp,
                        vertical = 32.dp
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                PrimaryButton(
                    text = "Entrar",
                    onClick = onLoginClick,
                    modifier = Modifier.fillMaxWidth()
                )

                SecondaryButton(
                    text = "Criar conta",
                    onClick = onRegisterClick,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Juntos por um transporte mais seguro e respeitoso.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(
    name = "WelcomeScreen - Tema claro",
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun WelcomeScreenLightPreview() {
    SafeBusTheme(darkTheme = false) {
        WelcomeScreen(
            onLoginClick = {},
            onRegisterClick = {}
        )
    }
}

@Preview(
    name = "WelcomeScreen - Tema escuro",
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun WelcomeScreenDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        WelcomeScreen(
            onLoginClick = {},
            onRegisterClick = {}
        )
    }
}
