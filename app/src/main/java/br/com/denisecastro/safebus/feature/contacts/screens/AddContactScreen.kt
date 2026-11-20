package br.com.denisecastro.safebus.feature.contacts.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.denisecastro.safebus.R
import br.com.denisecastro.safebus.feature.contacts.components.form.ContactForm
import br.com.denisecastro.safebus.ui.components.buttons.primary.PrimaryButton
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme

@Composable
fun AddContactScreen(
    onBackClick: () -> Unit,
    onSaveClick: (String, String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    var name by rememberSaveable { mutableStateOf("") }
    var phone by rememberSaveable { mutableStateOf("") }
    var relationship by rememberSaveable { mutableStateOf("") }

    val canSave = name.isNotBlank() && phone.isNotBlank()

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .windowInsetsPadding(WindowInsets.navigationBars)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            IconButton(
                onClick = onBackClick
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.back)
                )
            }

            Text(
                text = stringResource(R.string.contact_add_title),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                text = stringResource(R.string.contact_add_description),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            ContactForm(
                name = name,
                onNameChange = { name = it },
                phone = phone,
                onPhoneChange = { phone = it },
                relationship = relationship,
                onRelationshipChange = { relationship = it }
            )

            PrimaryButton(
                text = stringResource(R.string.contact_save),
                onClick = {
                    onSaveClick(
                        name.trim(),
                        phone.trim(),
                        relationship.trim()
                    )
                },
                enabled = canSave,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(name = "Adicionar contato - Claro", showSystemUi = true)
@Composable
private fun AddContactScreenLightPreview() {
    SafeBusTheme(darkTheme = false) {
        AddContactScreen(
            onBackClick = {},
            onSaveClick = { _, _, _ -> }
        )
    }
}

@Preview(name = "Adicionar contato - Escuro", showSystemUi = true)
@Composable
private fun AddContactScreenDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        AddContactScreen(
            onBackClick = {},
            onSaveClick = { _, _, _ -> }
        )
    }
}


