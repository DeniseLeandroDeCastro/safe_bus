
package br.com.denisecastro.safebus.feature.welcome.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.denisecastro.safebus.R
import br.com.denisecastro.safebus.feature.welcome.components.features.WelcomeFeature
import br.com.denisecastro.safebus.feature.welcome.components.header.WelcomeHeader
import br.com.denisecastro.safebus.ui.components.buttons.primary.PrimaryButton
import br.com.denisecastro.safebus.ui.components.buttons.secondary.SecondaryButton
import br.com.denisecastro.safebus.ui.theme.AlertDanger
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme
import br.com.denisecastro.safebus.ui.theme.WelcomeBlue
import br.com.denisecastro.safebus.ui.theme.WelcomeCyan

@Composable
fun WelcomeScreen(
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = MaterialTheme.colorScheme
    val scrollState = rememberScrollState()

    Surface(
        modifier = modifier.fillMaxSize(),
        color = colors.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.navigationBars)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Cabeçalho
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 24.dp,
                        end = 24.dp,
                        top = 32.dp
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                WelcomeHeader()

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "Sua segurança",
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.ExtraBold,
                        color = colors.onBackground,
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = "acompanha você.",
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.ExtraBold,
                        color = WelcomeCyan,
                        textAlign = TextAlign.Center
                    )
                }

                Text(
                    text = "Um aplicativo para tornar o transporte público mais seguro, com alertas rápidos e apoio quando você precisar.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = colors.onSurfaceVariant,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Ilustração aprovada
            Image(
                painter = painterResource(id = R.drawable.welcome),
                contentDescription = "Passageira com celular em um ponto de ônibus",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.55f),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Funcionalidades, botões e rodapé
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 20.dp,
                        end = 20.dp,
                        bottom = 40.dp
                    ),
                verticalArrangement = Arrangement.spacedBy(28.dp)
            ) {

                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    color = colors.surface,
                    shadowElevation = 3.dp
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = 10.dp,
                                vertical = 22.dp
                            ),
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        WelcomeFeature(
                            title = "Alertas rápidos",
                            description = "Em situações de risco.",
                            icon = Icons.Filled.NotificationsActive,
                            accentColor = AlertDanger,
                            modifier = Modifier.weight(1f)
                        )

                        WelcomeFeature(
                            title = "Geolocalização",
                            description = "Ajuda mais próxima.",
                            icon = Icons.Filled.LocationOn,
                            accentColor = WelcomeCyan,
                            modifier = Modifier.weight(1f)
                        )

                        WelcomeFeature(
                            title = "Mais segurança",
                            description = "Para todos ao seu redor.",
                            icon = Icons.Filled.Groups,
                            accentColor = WelcomeBlue,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(18.dp)
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
                }

                Text(
                    text = "Juntos por um transporte mais seguro e respeitoso.",
                    style = MaterialTheme.typography.bodySmall,
                    color = colors.onSurfaceVariant,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Preview(
    name = "WelcomeScreen - Tema claro",
    showBackground = true,
    showSystemUi = true,
    widthDp = 390,
    heightDp = 844
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
    showSystemUi = true,
    widthDp = 390,
    heightDp = 844
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
