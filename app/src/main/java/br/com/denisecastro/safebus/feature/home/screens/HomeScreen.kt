package br.com.denisecastro.safebus.feature.home.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.ReportProblem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.Alignment
import br.com.denisecastro.safebus.feature.home.components.cards.HomeAlertCard
import br.com.denisecastro.safebus.feature.home.components.cards.SafetyTipsCard
import br.com.denisecastro.safebus.feature.home.components.cards.TransportInfoCard
import br.com.denisecastro.safebus.feature.home.model.HomeUiState
import br.com.denisecastro.safebus.ui.theme.SafeBusAlertColors
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme

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
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f)
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
                        text = "Boa viagem!",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                IconButton(onClick = onNotificationsClick) {
                    Icon(
                        imageVector = Icons.Filled.NotificationsNone,
                        contentDescription = "Notificações",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }

                IconButton(onClick = onProfileClick) {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Perfil",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

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

            Text(
                text = "Precisa de ajuda?",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            HomeAlertCard(
                title = "Assalto ou situação de perigo",
                icon = Icons.Filled.Warning,
                backgroundColor = dangerColors.background,
                accentColor = dangerColors.accent,
                contentColor = dangerColors.content,
                onClick = onDangerAlertClick
            )

            HomeAlertCard(
                title = "Importunação ou assédio sexual",
                icon = Icons.Filled.ReportProblem,
                backgroundColor = harassmentColors.background,
                accentColor = harassmentColors.accent,
                contentColor = harassmentColors.content,
                onClick = onHarassmentAlertClick
            )

            Spacer(modifier = Modifier.padding(2.dp))

            SafetyTipsCard(
                onClick = onSafetyTipsClick
            )
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
            userName = "Ana",
            transportLine = "320",
            transportOrigin = "Centro",
            transportDestination = "Bairro"
        ),
        onNotificationsClick = {},
        onProfileClick = {},
        onTransportClick = {},
        onDangerAlertClick = {},
        onHarassmentAlertClick = {},
        onSafetyTipsClick = {}
    )
}
