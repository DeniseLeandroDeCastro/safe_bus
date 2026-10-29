package br.com.denisecastro.safebus.feature.dangeralert.components.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
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
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme

@Composable
fun AlertSendErrorCard(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.error,
            contentColor = MaterialTheme.colorScheme.onError
        )
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.WarningAmber,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onError
            )

            Text(
                text = stringResource(R.string.alert_send_error),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onError,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview(name = "Erro no envio - Claro", showSystemUi = true)
@Composable
private fun AlertSendErrorCardLightPreview() {
    SafeBusTheme(darkTheme = false) {
        AlertSendErrorCard(modifier = Modifier.padding(16.dp))
    }
}

@Preview(name = "Erro no envio - Escuro", showSystemUi = true)
@Composable
private fun AlertSendErrorCardDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        AlertSendErrorCard(modifier = Modifier.padding(16.dp))
    }
}


