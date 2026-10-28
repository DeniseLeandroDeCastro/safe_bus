package br.com.denisecastro.safebus.feature.home.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material.icons.filled.PanTool
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.denisecastro.safebus.R
import br.com.denisecastro.safebus.feature.home.components.cards.HomeAlertCard
import br.com.denisecastro.safebus.feature.home.components.cards.SafetyTipsCard
import br.com.denisecastro.safebus.feature.home.components.cards.TransportInfoCard
import br.com.denisecastro.safebus.feature.home.components.cards.TravelSafetyCard
import br.com.denisecastro.safebus.feature.home.model.HomeUiState
import br.com.denisecastro.safebus.ui.theme.AlertDanger
import br.com.denisecastro.safebus.ui.theme.SafeBusAlertColors
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme
import br.com.denisecastro.safebus.ui.theme.TurquoiseSecondary

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onNotificationsClick: () -> Unit,
    onProfileClick: () -> Unit,
    onTransportClick: () -> Unit,
    onDangerAlertClick: () -> Unit,
    onHarassmentAlertClick: () -> Unit,
    onSafetyTipsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val dangerColors = SafeBusAlertColors.danger()
    val harassmentColors = SafeBusAlertColors.harassment()

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.navigationBars)
                .verticalScroll(rememberScrollState())
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                    top = 20.dp,
                    bottom = 24.dp
                ),
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {

            // Cabeçalho
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = if (uiState.userName.isBlank()) {
                            "Olá!"
                        } else {
                            "Olá, ${uiState.userName}!"
                        },
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Text(
                        text = "Mais segurança nas suas viagens.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                IconButton(onClick = onNotificationsClick) {
                    Icon(
                        imageVector = Icons.Filled.NotificationsNone,
                        contentDescription = "Notificações",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }

                IconButton(
                    onClick = onProfileClick,
                    modifier = Modifier.background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = CircleShape
                    )
                ) {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Perfil",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            // Card ilustrado
            TravelSafetyCard()

            // Informações de transporte, quando disponíveis
            if (
                uiState.transportLine.isNotBlank() &&
                uiState.transportOrigin.isNotBlank() &&
                uiState.transportDestination.isNotBlank()
            ) {
                TransportInfoCard(
                    line = uiState.transportLine,
                    origin = uiState.transportOrigin,
                    destination = uiState.transportDestination,
                    onClick = onTransportClick
                )
            }

            // Alertas de emergência
            Column(
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Text(
                    text = "Alertas de emergência",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    HomeAlertCard(
                        title = "Assalto ou situação de perigo",
                        description = "Acione uma viatura próxima.",
                        imageRes = R.drawable.alert_danger,
                        accentColor = AlertDanger,
                        onClick = onDangerAlertClick,
                        modifier = Modifier.weight(1f)
                    )

                    HomeAlertCard(
                        title = "Importunação ou assédio sexual",
                        description = "Peça ajuda de forma discreta.",
                        imageRes = R.drawable.alert_harassment,
                        accentColor = TurquoiseSecondary,
                        onClick = onHarassmentAlertClick,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            // Dicas de segurança
            Column(
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                SafetyTipsCard(
                    onClick = onSafetyTipsClick
                )
            }
        }
    }
}

@Preview(
    name = "Home - Tema claro",
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun HomeScreenLightPreview() {
    SafeBusTheme(darkTheme = false) {
        HomeScreenPreviewContent()
    }
}

@Preview(
    name = "Home - Tema escuro",
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun HomeScreenDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        HomeScreenPreviewContent()
    }
}

@Composable
private fun HomeScreenPreviewContent() {
    HomeScreen(
        uiState = HomeUiState(
            userName = "Ana"
        ),
        onNotificationsClick = {},
        onProfileClick = {},
        onTransportClick = {},
        onDangerAlertClick = {},
        onHarassmentAlertClick = {},
        onSafetyTipsClick = {}
    )
}
