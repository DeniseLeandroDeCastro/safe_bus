package br.com.denisecastro.safebus.feature.dangeralert.components.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
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
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme

@Composable
fun AlertLocationInfoCard(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.Info,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )

            Text(
                text = stringResource(R.string.alert_location_info),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview(name = "Orientações - Tema claro", showSystemUi = true)
@Composable
private fun AlertLocationInfoCardLightPreview() {
    SafeBusTheme(darkTheme = false) {
        AlertLocationInfoCard(
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(name = "Orientações - Tema escuro", showSystemUi = true)
@Composable
private fun AlertLocationInfoCardDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        AlertLocationInfoCard(
            modifier = Modifier.padding(16.dp)
        )
    }
}
