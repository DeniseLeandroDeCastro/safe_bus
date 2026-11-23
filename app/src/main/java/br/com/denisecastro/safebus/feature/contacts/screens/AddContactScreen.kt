package br.com.denisecastro.safebus.feature.contacts.screens

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.denisecastro.safebus.R
import br.com.denisecastro.safebus.feature.contacts.components.form.ContactForm
import br.com.denisecastro.safebus.feature.contacts.components.photo.ContactPhotoPicker
import br.com.denisecastro.safebus.feature.contacts.data.model.ContactUiState
import br.com.denisecastro.safebus.feature.contacts.viewmodel.ContactsViewModel
import br.com.denisecastro.safebus.ui.components.buttons.primary.PrimaryButton
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme

@Composable
fun AddContactScreen(
    onBackClick: () -> Unit,
    onSaveSuccess: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ContactsViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    var photoUriString by rememberSaveable {
        mutableStateOf<String?>(null)
    }

    AddContactContent(
        uiState = uiState,
        photoUri = photoUriString?.let(Uri::parse),
        onPhotoSelected = { uri ->
            photoUriString = uri.toString()
        },
        onNameChange = viewModel::onNameChange,
        onPhoneChange = viewModel::onPhoneChange,
        onRelationshipChange = viewModel::onRelationshipChange,
        onBackClick = onBackClick,
        onSaveClick = {
            viewModel.saveContact(onSuccess = onSaveSuccess)
        },
        modifier = modifier
    )
}

@Composable
private fun AddContactContent(
    uiState: ContactUiState,
    photoUri: Uri?,
    onPhotoSelected: (Uri) -> Unit,
    onNameChange: (String) -> Unit,
    onPhoneChange: (String) -> Unit,
    onRelationshipChange: (String) -> Unit,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
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
                .imePadding()
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 20.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back)
                        )
                    }

                    Text(
                        text = stringResource(R.string.contact_add_title),
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }

                Text(
                    text = stringResource(R.string.contact_add_description),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                ContactPhotoPicker(
                    photoUri = photoUri,
                    onPhotoSelected = onPhotoSelected,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                ContactForm(
                    name = uiState.name,
                    onNameChange = onNameChange,
                    phone = uiState.phone,
                    onPhoneChange = onPhoneChange,
                    relationship = uiState.relationship,
                    onRelationshipChange = onRelationshipChange
                )

                uiState.errorMessage?.let { message ->
                    Text(
                        text = message,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }

            PrimaryButton(
                text = stringResource(R.string.contact_save),
                onClick = onSaveClick,
                enabled = uiState.isFormValid && !uiState.isLoading,
                isLoading = uiState.isLoading,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 20.dp,
                        end = 20.dp,
                        top = 16.dp,
                        bottom = 20.dp
                    )
            )
        }
    }
}

@Preview(name = "Adicionar contato - Claro", showSystemUi = true)
@Composable
private fun AddContactScreenLightPreview() {
    SafeBusTheme(darkTheme = false) {
        AddContactContent(
            uiState = ContactUiState(
                name = "Maria Silva",
                phone = "(84) 99999-9999",
                relationship = "Mãe"
            ),
            photoUri = null,
            onPhotoSelected = {},
            onNameChange = {},
            onPhoneChange = {},
            onRelationshipChange = {},
            onBackClick = {},
            onSaveClick = {}
        )
    }
}

@Preview(name = "Adicionar contato - Escuro", showSystemUi = true)
@Composable
private fun AddContactScreenDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        AddContactContent(
            uiState = ContactUiState(
                name = "Maria Silva",
                phone = "(84) 99999-9999",
                relationship = "Mãe"
            ),
            photoUri = null,
            onPhotoSelected = {},
            onNameChange = {},
            onPhoneChange = {},
            onRelationshipChange = {},
            onBackClick = {},
            onSaveClick = {}
        )
    }
}
