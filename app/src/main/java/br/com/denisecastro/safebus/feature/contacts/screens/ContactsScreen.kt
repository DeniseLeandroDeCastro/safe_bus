package br.com.denisecastro.safebus.feature.contacts.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.denisecastro.safebus.R
import br.com.denisecastro.safebus.feature.contacts.components.cards.ContactCard
import br.com.denisecastro.safebus.feature.contacts.components.empty.ContactsEmptyState
import br.com.denisecastro.safebus.feature.contacts.data.model.Contact
import br.com.denisecastro.safebus.feature.contacts.viewmodel.ContactsViewModel
import br.com.denisecastro.safebus.ui.components.buttons.primary.PrimaryButton
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme

@Composable
fun ContactsScreen(
    onAddContactClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ContactsViewModel = viewModel()
) {
    val contacts by viewModel.contacts.collectAsState()
    val isLoading by viewModel.isLoadingContacts.collectAsState()
    val errorMessage by viewModel.contactsError.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadContacts()
    }

    ContactsContent(
        contacts = contacts,
        isLoading = isLoading,
        errorMessage = errorMessage,
        onAddContactClick = onAddContactClick,
        modifier = modifier
    )
}

@Composable
private fun ContactsContent(
    contacts: List<Contact>,
    isLoading: Boolean,
    errorMessage: String?,
    onAddContactClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.navigationBars),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = stringResource(R.string.contacts_title),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                text = stringResource(R.string.contacts_description),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                when {
                    isLoading -> {
                        CircularProgressIndicator()
                    }

                    errorMessage != null -> {
                        Text(
                            text = errorMessage,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.error
                        )
                    }

                    contacts.isEmpty() -> {
                        ContactsEmptyState()
                    }

                    else -> {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(
                                items = contacts,
                                key = { it.id }
                            ) { contact ->
                                ContactCard(contact = contact)
                            }
                        }
                    }
                }
            }

            PrimaryButton(
                text = stringResource(R.string.contacts_add),
                onClick = onAddContactClick,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(
    name = "Contatos vazios - Claro",
    showSystemUi = true
)
@Composable
private fun ContactsEmptyLightPreview() {
    SafeBusTheme(darkTheme = false) {
        ContactsContent(
            contacts = emptyList(),
            isLoading = false,
            errorMessage = null,
            onAddContactClick = {}
        )
    }
}

@Preview(
    name = "Contatos vazios - Escuro",
    showSystemUi = true
)
@Composable
private fun ContactsEmptyDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        ContactsContent(
            contacts = emptyList(),
            isLoading = false,
            errorMessage = null,
            onAddContactClick = {}
        )
    }
}

@Preview(
    name = "Contatos cadastrados - Claro",
    showSystemUi = true
)
@Composable
private fun ContactsListLightPreview() {
    SafeBusTheme(darkTheme = false) {
        ContactsContent(
            contacts = listOf(
                Contact(
                    id = "1",
                    name = "Maria Silva",
                    phone = "(84) 99999-9999",
                    relationship = "Mãe"
                ),
                Contact(
                    id = "2",
                    name = "João Souza",
                    phone = "(84) 98888-8888",
                    relationship = "Amigo"
                )
            ),
            isLoading = false,
            errorMessage = null,
            onAddContactClick = {}
        )
    }
}

@Preview(
    name = "Contatos cadastrados - Escuro",
    showSystemUi = true
)
@Composable
private fun ContactsListDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        ContactsContent(
            contacts = listOf(
                Contact(
                    id = "1",
                    name = "Maria Silva",
                    phone = "(84) 99999-9999",
                    relationship = "Mãe"
                ),
                Contact(
                    id = "2",
                    name = "João Souza",
                    phone = "(84) 98888-8888",
                    relationship = "Amigo"
                )
            ),
            isLoading = false,
            errorMessage = null,
            onAddContactClick = {}
        )
    }
}
