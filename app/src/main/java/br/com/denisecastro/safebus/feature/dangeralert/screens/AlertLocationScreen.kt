package br.com.denisecastro.safebus.feature.dangeralert.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.WarningAmber
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.denisecastro.safebus.R
import br.com.denisecastro.safebus.feature.dangeralert.components.cards.AlertLocationInfoCard
import br.com.denisecastro.safebus.feature.dangeralert.components.cards.AlertLocationStatusCard
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
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(180.dp)
                    .background(
                        MaterialTheme.colorScheme.primaryContainer,
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(116.dp)
                        .background(
                            MaterialTheme.colorScheme.surface,
                            CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.LocationOn,
                        contentDescription = null,
                        modifier = Modifier.size(76.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = stringResource(R.string.alert_location_title),
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Text(
                    text = stringResource(R.string.alert_location_description),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.large,
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainerLow
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    AlertLocationStatusCard(
                        uiState = uiState
                    )
                }
            }

            AlertLocationInfoCard()

            Spacer(modifier = Modifier.height(8.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                when (uiState) {
                    is AlertLocationUiState.Success -> {
                        Button(
                            onClick = onContinueClick,
                            modifier = Modifier.fillMaxWidth(),
                            shape = MaterialTheme.shapes.extraLarge
                        ) {
                            Text(
                                text = stringResource(
                                    R.string.alert_location_continue
                                ),
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                        }
                    }

                    AlertLocationUiState.PermissionRequired,
                    AlertLocationUiState.PermissionDenied -> {
                        Button(
                            onClick = onPermissionClick,
                            modifier = Modifier.fillMaxWidth(),
                            shape = MaterialTheme.shapes.extraLarge
                        ) {
                            Text(
                                text = stringResource(
                                    R.string.alert_location_allow
                                ),
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                        }
                    }

                    AlertLocationUiState.LocationUnavailable -> {
                        Button(
                            onClick = onRetryClick,
                            modifier = Modifier.fillMaxWidth(),
                            shape = MaterialTheme.shapes.extraLarge
                        ) {
                            Text(
                                text = stringResource(
                                    R.string.alert_location_retry
                                ),
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                        }
                    }

                    AlertLocationUiState.Loading -> {
                        Button(
                            onClick = {},
                            enabled = false,
                            modifier = Modifier.fillMaxWidth(),
                            shape = MaterialTheme.shapes.extraLarge
                        ) {
                            Text(
                                text = stringResource(
                                    R.string.alert_location_loading
                                ),
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                        }
                    }
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
