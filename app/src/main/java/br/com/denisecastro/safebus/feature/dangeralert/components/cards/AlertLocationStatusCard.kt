package br.com.denisecastro.safebus.feature.dangeralert.components.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.WarningAmber
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.denisecastro.safebus.R
import br.com.denisecastro.safebus.feature.dangeralert.model.AlertLocationUiState
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme

@Composable
fun AlertLocationStatusCard(
    uiState: AlertLocationUiState,
    modifier: Modifier = Modifier
) {
    val isError = uiState == AlertLocationUiState.PermissionDenied ||
            uiState == AlertLocationUiState.LocationUnavailable

    val backgroundColor = if (isError) {
        MaterialTheme.colorScheme.error
    } else {
        MaterialTheme.colorScheme.surfaceContainerLow
    }

    val contentColor = if (isError) {
        MaterialTheme.colorScheme.onError
    } else {
        MaterialTheme.colorScheme.onSurface
    }

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor,
            contentColor = contentColor
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            when (uiState) {
                AlertLocationUiState.Loading -> {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(28.dp),
                            strokeWidth = 3.dp
                        )

                        Text(
                            text = stringResource(
                                R.string.alert_location_status_loading
                            ),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }

                is AlertLocationUiState.Success -> {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.CheckCircle,
                            contentDescription = null,
                            modifier = Modifier.size(30.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )

                        Text(
                            text = stringResource(
                                R.string.alert_location_status_success
                            ),
                            style = MaterialTheme.typography.titleMedium
                        )
                    }

                    Text(
                        text = stringResource(
                            R.string.alert_location_success_description
                        ),
                        style = MaterialTheme.typography.bodyMedium
                    )

                    uiState.accuracyMeters?.let { accuracy ->
                        Text(
                            text = stringResource(
                                R.string.alert_location_accuracy,
                                accuracy
                            ),
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }

                AlertLocationUiState.PermissionRequired,
                AlertLocationUiState.PermissionDenied,
                AlertLocationUiState.LocationUnavailable -> {
                    Icon(
                        imageVector = Icons.Outlined.WarningAmber,
                        contentDescription = null,
                        modifier = Modifier.size(36.dp),
                        tint = contentColor
                    )

                    val messageRes = when (uiState) {
                        AlertLocationUiState.PermissionRequired ->
                            R.string.alert_location_permission_description

                        AlertLocationUiState.PermissionDenied ->
                            R.string.alert_location_denied

                        else ->
                            R.string.alert_location_unavailable
                    }

                    Text(
                        text = stringResource(messageRes),
                        style = MaterialTheme.typography.bodyLarge,
                        color = contentColor
                    )
                }
            }
        }
    }
}

@Preview(name = "Status - Erro - Claro", showSystemUi = true)
@Composable
private fun AlertLocationStatusCardLightPreview() {
    SafeBusTheme(darkTheme = false) {
        AlertLocationStatusCard(
            uiState = AlertLocationUiState.LocationUnavailable
        )
    }
}

@Preview(name = "Status - Erro - Escuro", showSystemUi = true)
@Composable
private fun AlertLocationStatusCardDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        AlertLocationStatusCard(
            uiState = AlertLocationUiState.LocationUnavailable
        )
    }
}
