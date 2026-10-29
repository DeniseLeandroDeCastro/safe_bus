package br.com.denisecastro.safebus.feature.dangeralert.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import br.com.denisecastro.safebus.feature.dangeralert.model.AlertLocationUiState
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme
import br.com.denisecastro.safebus.feature.dangeralert.components.cards.AlertReviewInfoCard

@Composable
fun AlertReviewScreen(
    location: AlertLocationUiState.Success,
    onConfirmClick: () -> Unit,
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
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                text = stringResource(R.string.alert_review_title),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = stringResource(R.string.alert_review_heading),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = stringResource(R.string.alert_review_description),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            AlertReviewInfoCard(location = location)

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Info,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )

                    Text(
                        text = stringResource(R.string.alert_review_warning),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = onConfirmClick,
                    enabled = false,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(stringResource(R.string.alert_review_confirm))
                }

                OutlinedButton(
                    onClick = onBackClick,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(stringResource(R.string.alert_review_back))
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
            onBackClick = {}
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
            onBackClick = {}
        )
    }
}
