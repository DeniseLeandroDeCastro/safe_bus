package br.com.denisecastro.safebus.feature.contacts.components.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.denisecastro.safebus.feature.contacts.data.model.Contact
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme

@Composable
fun ContactCard(
    contact: Contact,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.PersonOutline,
                contentDescription = null,
                modifier = Modifier.size(32.dp),
                tint = MaterialTheme.colorScheme.primary
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = contact.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = contact.phone,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                if (contact.relationship.isNotBlank()) {
                    Text(
                        text = contact.relationship,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

@Preview(name = "Contato - Claro", showBackground = true)
@Composable
private fun ContactCardLightPreview() {
    SafeBusTheme(darkTheme = false) {
        ContactCard(
            contact = Contact(
                name = "Maria Silva",
                phone = "(84) 99999-9999",
                relationship = "Mãe"
            ),
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(name = "Contato - Escuro", showBackground = true)
@Composable
private fun ContactCardDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        ContactCard(
            contact = Contact(
                name = "João Souza",
                phone = "(84) 98888-8888",
                relationship = "Amigo"
            ),
            modifier = Modifier.padding(16.dp)
        )
    }
}
