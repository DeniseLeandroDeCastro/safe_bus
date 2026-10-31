package br.com.denisecastro.safebus.ui.components.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.denisecastro.safebus.R
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme

@Composable
fun StandardCard(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(16.dp),
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(contentPadding),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            content = content
        )
    }
}

@Preview(name = "StandardCard - Claro", showBackground = true)
@Composable
private fun StandardCardLightPreview() {
    SafeBusTheme(darkTheme = false) {
        StandardCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(R.string.standard_card_transport_title),
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = stringResource(R.string.standard_card_transport_example),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview(name = "StandardCard - Escuro", showBackground = true)
@Composable
private fun StandardCardDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        StandardCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(R.string.standard_card_transport_title),
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = stringResource(R.string.standard_card_transport_example),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview(
    name = "StandardCard - Conteúdo personalizado",
    showBackground = true
)
@Composable
private fun StandardCardCustomPreview() {
    SafeBusTheme(darkTheme = false) {
        StandardCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentPadding = PaddingValues(20.dp)
        ) {
            Text(
                text = stringResource(R.string.standard_card_safety_title),
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = stringResource(R.string.standard_card_safety_description),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
