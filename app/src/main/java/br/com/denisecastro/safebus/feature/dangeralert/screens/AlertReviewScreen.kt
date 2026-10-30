package br.com.denisecastro.safebus.feature.dangeralert.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.denisecastro.safebus.R
import br.com.denisecastro.safebus.feature.dangeralert.components.cards.AlertReviewInfoCard
import br.com.denisecastro.safebus.feature.dangeralert.components.cards.AlertReviewWarningCard
import br.com.denisecastro.safebus.feature.dangeralert.components.cards.AlertSendErrorCard
import br.com.denisecastro.safebus.feature.dangeralert.data.model.EmergencyAlertSendState
import br.com.denisecastro.safebus.feature.dangeralert.model.AlertLocationUiState
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme

@Composable
fun AlertReviewScreen(
    location: AlertLocationUiState.Success,
    onConfirmClick: () -> Unit,
    onBackClick: () -> Unit,
    sendState: EmergencyAlertSendState,
    modifier: Modifier = Modifier
) {
    val isLoading = sendState == EmergencyAlertSendState.Loading

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
                Text(
                    text = stringResource(R.string.alert_review_title),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Text(
                    text = stringResource(R.string.alert_review_heading),
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Text(
                    text = stringResource(R.string.alert_review_description),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                AlertReviewInfoCard(
                    location = location
                )

                if (sendState == EmergencyAlertSendState.Error) {
                    AlertSendErrorCard()
                }

                AlertReviewWarningCard()
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = onConfirmClick,
                    enabled = sendState == EmergencyAlertSendState.Idle ||
                            sendState == EmergencyAlertSendState.Error,
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.extraLarge
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(20.dp),
                            strokeWidth = 2.dp,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    } else {
                        Text(
                            text = stringResource(R.string.alert_review_confirm),
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                }

                OutlinedButton(
                    onClick = onBackClick,
                    enabled = !isLoading,
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.extraLarge
                ) {
                    Text(
                        text = stringResource(R.string.alert_review_back),
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }
        }
    }
}

@Preview(name = "Revisão - Claro", showSystemUi = true)
@Composable
private fun AlertReviewScreenLightPreview() {
    SafeBusTheme(darkTheme = false) {
        AlertReviewScreen(
            location = AlertLocationUiState.Success(
                latitude = -5.79,
                longitude = -35.21,
                accuracyMeters = 10f
            ),
            onConfirmClick = {},
            onBackClick = {},
            sendState = EmergencyAlertSendState.Idle
        )
    }
}

@Preview(name = "Revisão - Escuro", showSystemUi = true)
@Composable
private fun AlertReviewScreenDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        AlertReviewScreen(
            location = AlertLocationUiState.Success(
                latitude = -5.79,
                longitude = -35.21,
                accuracyMeters = 10f
            ),
            onConfirmClick = {},
            onBackClick = {},
            sendState = EmergencyAlertSendState.Idle
        )
    }
}
