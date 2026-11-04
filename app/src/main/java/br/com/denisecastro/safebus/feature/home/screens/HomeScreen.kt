package br.com.denisecastro.safebus.feature.home.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.denisecastro.safebus.feature.home.components.cards.SafetyTipsCard
import br.com.denisecastro.safebus.feature.home.components.cards.TransportInfoCard
import br.com.denisecastro.safebus.feature.home.components.cards.TravelSafetyCard
import br.com.denisecastro.safebus.feature.home.components.header.HomeHeader
import br.com.denisecastro.safebus.feature.home.components.sections.HomeAlertsSection
import br.com.denisecastro.safebus.feature.home.model.HomeUiState
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
    onLogoutClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
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
            HomeHeader(
                userName = uiState.userName,
                photoUrl = uiState.photoUrl,
                onNotificationsClick = onNotificationsClick,
                onProfileClick = onProfileClick,
                onLogoutClick = onLogoutClick
            )

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
            HomeAlertsSection(
                onDangerAlertClick = onDangerAlertClick,
                onHarassmentAlertClick = onHarassmentAlertClick
            )

            // Dicas de segurança
            SafetyTipsCard(
                onClick = onSafetyTipsClick
            )
        }
    }
}

@Preview(
    name = "Home - Tema claro",
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
        onLogoutClick = {},
        onSafetyTipsClick = {}
    )
}
