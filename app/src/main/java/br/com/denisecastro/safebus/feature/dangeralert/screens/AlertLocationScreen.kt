
package br.com.denisecastro.safebus.feature.dangeralert.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.denisecastro.safebus.R
import br.com.denisecastro.safebus.feature.dangeralert.components.cards.AlertLocationInfoCard
import br.com.denisecastro.safebus.feature.dangeralert.components.cards.AlertLocationStatusCard
import br.com.denisecastro.safebus.feature.dangeralert.components.header.AlertLocationHeader
import br.com.denisecastro.safebus.feature.dangeralert.model.AlertLocationUiState
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme

@Composable
fun AlertLocationScreen(
    uiState: AlertLocationUiState,
    onPermissionClick: () -> Unit,
    onRetryClick: () -> Unit,
    onContinueClick: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.navigationBars)
                .padding(horizontal = 20.dp, vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                AlertLocationHeader()

                AlertLocationStatusCard(
                    uiState = uiState
                )

                AlertLocationInfoCard()
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                val buttonText = when (uiState) {
                    is AlertLocationUiState.Success ->
                        R.string.alert_location_continue

                    AlertLocationUiState.PermissionRequired,
                    AlertLocationUiState.PermissionDenied ->
                        R.string.alert_location_allow

                    AlertLocationUiState.LocationUnavailable ->
                        R.string.alert_location_retry

                    AlertLocationUiState.Loading ->
                        R.string.alert_location_loading
                }

                Button(
                    onClick = {
                        when (uiState) {
                            is AlertLocationUiState.Success ->
                                onContinueClick()

                            AlertLocationUiState.PermissionRequired,
                            AlertLocationUiState.PermissionDenied ->
                                onPermissionClick()

                            AlertLocationUiState.LocationUnavailable ->
                                onRetryClick()

                            AlertLocationUiState.Loading -> Unit
                        }
                    },
                    enabled = uiState != AlertLocationUiState.Loading,
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.extraLarge
                ) {
                    Text(
                        text = stringResource(buttonText),
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }

                OutlinedButton(
                    onClick = onBackClick,
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.extraLarge
                ) {
                    Text(
                        text = stringResource(R.string.alert_location_back),
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }
        }
    }
}

@Preview(name = "Localização - Claro", showSystemUi = true)
@Composable
private fun AlertLocationLightPreview() {
    SafeBusTheme(darkTheme = false) {
        AlertLocationScreen(
            uiState = AlertLocationUiState.Success(
                latitude = -5.79,
                longitude = -35.21,
                accuracyMeters = 5f
            ),
            onPermissionClick = {},
            onRetryClick = {},
            onContinueClick = {},
            onBackClick = {}
        )
    }
}

@Preview(name = "Localização - Escuro", showSystemUi = true)
@Composable
private fun AlertLocationDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        AlertLocationScreen(
            uiState = AlertLocationUiState.Success(
                latitude = -5.79,
                longitude = -35.21,
                accuracyMeters = 5f
            ),
            onPermissionClick = {},
            onRetryClick = {},
            onContinueClick = {},
            onBackClick = {}
        )
    }
}
