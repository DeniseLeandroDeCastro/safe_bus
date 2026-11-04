package br.com.denisecastro.safebus.feature.home.components.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.denisecastro.safebus.R
import br.com.denisecastro.safebus.feature.home.components.cards.HomeAlertCard
import br.com.denisecastro.safebus.ui.theme.SafeBusAlertColors
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme

@Composable
fun HomeAlertsSection(
    onDangerAlertClick: () -> Unit,
    onHarassmentAlertClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val dangerColors = SafeBusAlertColors.danger()
    val harassmentColors = SafeBusAlertColors.harassment()

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text(
            text = stringResource(R.string.home_emergency_alerts),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.Top
        ) {
            HomeAlertCard(
                title = stringResource(R.string.home_danger_title),
                description = stringResource(R.string.home_danger_description),
                imageRes = R.drawable.alert_danger,
                accentColor = dangerColors.accent,
                onClick = onDangerAlertClick,
                modifier = Modifier.weight(1f)
            )

            HomeAlertCard(
                title = stringResource(R.string.home_harassment_title),
                description = stringResource(R.string.home_harassment_description),
                imageRes = R.drawable.alert_harassment,
                accentColor = harassmentColors.accent,
                onClick = onHarassmentAlertClick,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview(name = "Alertas - Claro", showBackground = true)
@Composable
private fun HomeAlertsSectionLightPreview() {
    SafeBusTheme(darkTheme = false) {
        Surface {
            HomeAlertsSection(
                onDangerAlertClick = {},
                onHarassmentAlertClick = {}
            )
        }
    }
}

@Preview(name = "Alertas - Escuro", showBackground = true)
@Composable
private fun HomeAlertsSectionDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        Surface {
            HomeAlertsSection(
                onDangerAlertClick = {},
                onHarassmentAlertClick = {}
            )
        }
    }
}


