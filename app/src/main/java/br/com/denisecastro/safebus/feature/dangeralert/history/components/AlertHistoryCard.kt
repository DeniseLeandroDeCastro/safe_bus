package br.com.denisecastro.safebus.feature.dangeralert.history.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.denisecastro.safebus.R
import br.com.denisecastro.safebus.feature.dangeralert.data.model.AlertHistoryItem
import br.com.denisecastro.safebus.ui.components.cards.StandardCard
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme
import br.com.denisecastro.safebus.utils.DateUtils
import com.google.firebase.Timestamp
import java.util.Date

@Composable
fun AlertHistoryCard(
    alert: AlertHistoryItem,
    modifier: Modifier = Modifier
) {
    val alertTitle = when (alert.type) {
        "DANGER" -> R.string.danger_alert_title
        "HARASSMENT" -> R.string.alert_review_harassment_title
        else -> R.string.alert_history_unknown_type
    }

    val alertImage = when (alert.type) {
        "DANGER" -> R.drawable.alert_danger
        "HARASSMENT" -> R.drawable.alert_harassment
        else -> null
    }

    val statusText = when (alert.status) {
        "REGISTERED" -> stringResource(R.string.alert_history_registered)
        else -> stringResource(R.string.alert_history_unknown_status)
    }

    val dateText = alert.createdAt?.toDate()?.let { date ->
        DateUtils.formatDateTime(date)
    } ?: stringResource(R.string.alert_history_unknown_date)

    StandardCard(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.Top
        ) {
            if (alertImage != null) {
                Image(
                    painter = painterResource(id = alertImage),
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = stringResource(alertTitle),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = dateText,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = statusText,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.LocationOn,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = stringResource(
                    R.string.alert_history_coordinates,
                    alert.latitude,
                    alert.longitude
                ),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

private val previewAlert = AlertHistoryItem(
    id = "preview-alert",
    type = "DANGER",
    latitude = -5.91,
    longitude = -35.26,
    accuracyMeters = 15.0,
    status = "REGISTERED",
    createdAt = Timestamp(Date(0))
)

private val previewHarassmentAlert = previewAlert.copy(
    id = "preview-harassment",
    type = "HARASSMENT"
)

@Preview(
    name = "Histórico - Assalto - Claro",
    showBackground = true
)
@Composable
private fun AlertHistoryCardLightPreview() {
    SafeBusTheme(darkTheme = false) {
        Surface(color = MaterialTheme.colorScheme.background) {
            AlertHistoryCard(alert = previewAlert)
        }
    }
}

@Preview(
    name = "Histórico - Assalto - Escuro",
    showBackground = true
)
@Composable
private fun AlertHistoryCardDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.background) {
            AlertHistoryCard(alert = previewAlert)
        }
    }
}

@Preview(
    name = "Histórico - Assédio - Claro",
    showBackground = true
)
@Composable
private fun AlertHistoryHarassmentLightPreview() {
    SafeBusTheme(darkTheme = false) {
        Surface(color = MaterialTheme.colorScheme.background) {
            AlertHistoryCard(alert = previewHarassmentAlert)
        }
    }
}

@Preview(
    name = "Histórico - Assédio - Escuro",
    showBackground = true
)
@Composable
private fun AlertHistoryHarassmentDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.background) {
            AlertHistoryCard(alert = previewHarassmentAlert)
        }
    }
}
