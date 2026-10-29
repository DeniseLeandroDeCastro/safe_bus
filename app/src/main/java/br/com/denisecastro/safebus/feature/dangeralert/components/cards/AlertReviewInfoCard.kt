package br.com.denisecastro.safebus.feature.dangeralert.components.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.WarningAmber
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
fun AlertReviewInfoCard(
    location: AlertLocationUiState.Success,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow
        )
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = stringResource(R.string.alert_review_type_label),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.WarningAmber,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.error
                )

                Text(
                    text = stringResource(R.string.danger_alert_title),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Text(
                text = stringResource(R.string.alert_review_location_label),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Row(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.LocationOn,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )

                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = stringResource(
                            R.string.alert_review_coordinates,
                            location.latitude,
                            location.longitude
                        ),
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Text(
                        text = location.accuracyMeters?.let {
                            stringResource(R.string.alert_review_accuracy, it)
                        } ?: stringResource(
                            R.string.alert_review_accuracy_unknown
                        ),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Preview(name = "Revisão - Card claro", showSystemUi = true)
@Composable
private fun AlertReviewInfoCardLightPreview() {
    SafeBusTheme(darkTheme = false) {
        AlertReviewInfoCard(
            location = AlertLocationUiState.Success(
                latitude = -5.79,
                longitude = -35.21,
                accuracyMeters = 10f
            ),
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(name = "Revisão - Card escuro", showSystemUi = true)
@Composable
private fun AlertReviewInfoCardDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        AlertReviewInfoCard(
            location = AlertLocationUiState.Success(
                latitude = -5.79,
                longitude = -35.21,
                accuracyMeters = 10f
            ),
            modifier = Modifier.padding(16.dp)
        )
    }
}
