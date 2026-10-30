package br.com.denisecastro.safebus.feature.dangeralert.components.cards

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.denisecastro.safebus.R
import br.com.denisecastro.safebus.feature.dangeralert.model.AlertLocationUiState
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme

@Composable
fun AlertReviewInfoCard(
    location: AlertLocationUiState.Success,
    modifier: Modifier = Modifier,
    alertType: String = "DANGER"
) {
    val isHarassment = alertType == "HARASSMENT"

    @DrawableRes
    val alertImage = if (isHarassment) {
        R.drawable.alert_harassment
    } else {
        R.drawable.alert_danger
    }

    val alertTitle = if (isHarassment) {
        R.string.alert_review_harassment_title
    } else {
        R.string.danger_alert_title
    }

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.extraLarge,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(92.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(MaterialTheme.colorScheme.surfaceContainerLow),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(alertImage),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = stringResource(R.string.alert_review_type_label),
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Text(
                        text = stringResource(alertTitle),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            HorizontalDivider(
                color = MaterialTheme.colorScheme.outlineVariant
            )

            Row(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(92.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(MaterialTheme.colorScheme.surfaceContainerLow),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(R.drawable.alert_location_map),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = stringResource(R.string.alert_review_location_label),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = stringResource(
                            R.string.alert_review_coordinates,
                            location.latitude,
                            location.longitude
                        ),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
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

@Preview(name = "Assalto - Claro", showBackground = true)
@Composable
private fun AlertReviewDangerLightPreview() {
    SafeBusTheme(darkTheme = false) {
        AlertReviewInfoCard(
            location = AlertLocationUiState.Success(-5.79, -35.21, 10f),
            alertType = "DANGER",
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(name = "Assalto - Escuro", showBackground = true)
@Composable
private fun AlertReviewDangerDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        AlertReviewInfoCard(
            location = AlertLocationUiState.Success(-5.79, -35.21, 10f),
            alertType = "DANGER",
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(name = "Assédio - Claro", showBackground = true)
@Composable
private fun AlertReviewHarassmentLightPreview() {
    SafeBusTheme(darkTheme = false) {
        AlertReviewInfoCard(
            location = AlertLocationUiState.Success(-5.79, -35.21, 10f),
            alertType = "HARASSMENT",
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(name = "Assédio - Escuro", showBackground = true)
@Composable
private fun AlertReviewHarassmentDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        AlertReviewInfoCard(
            location = AlertLocationUiState.Success(-5.79, -35.21, 10f),
            alertType = "HARASSMENT",
            modifier = Modifier.padding(16.dp)
        )
    }
}
