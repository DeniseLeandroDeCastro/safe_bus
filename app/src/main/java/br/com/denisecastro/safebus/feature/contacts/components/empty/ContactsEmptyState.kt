package br.com.denisecastro.safebus.feature.contacts.components.empty

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.denisecastro.safebus.R
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme

@Composable
fun ContactsEmptyState(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.img_contacts_empty),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .size(height = 220.dp, width = 300.dp)
        )

        Text(
            text = stringResource(R.string.contacts_empty_title),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center
        )

        Text(
            text = stringResource(R.string.contacts_empty_description),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(name = "Contatos vazios - Claro", showSystemUi = true)
@Composable
private fun ContactsEmptyStateLightPreview() {
    SafeBusTheme(darkTheme = false) {
        ContactsEmptyState()
    }
}

@Preview(name = "Contatos vazios - Escuro", showSystemUi = true)
@Composable
private fun ContactsEmptyStateDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        ContactsEmptyState()
    }
}
