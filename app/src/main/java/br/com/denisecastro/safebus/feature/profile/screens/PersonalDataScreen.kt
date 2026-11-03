package br.com.denisecastro.safebus.feature.profile.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.denisecastro.safebus.R
import br.com.denisecastro.safebus.feature.profile.components.InfoField
import br.com.denisecastro.safebus.feature.profile.components.cards.SectionCard
import br.com.denisecastro.safebus.feature.profile.components.fields.NameEditField
import br.com.denisecastro.safebus.feature.profile.model.ProfileUiState
import br.com.denisecastro.safebus.ui.components.buttons.primary.PrimaryButton
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme
import br.com.denisecastro.safebus.feature.profile.components.avatar.ProfileAvatar

@Composable
fun PersonalDataScreen(
    uiState: ProfileUiState,
    onBackClick: () -> Unit,
    onSaveName: (String) -> Unit,
    onClearNameUpdateResult: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isEditingName by remember { mutableStateOf(false) }
    var editedName by remember(uiState.name) {
        mutableStateOf(uiState.name)
    }

    LaunchedEffect(uiState.nameUpdateSuccess) {
        if (uiState.nameUpdateSuccess) {
            isEditingName = false
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.navigationBars)
            .padding(horizontal = 20.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                    contentDescription = stringResource(R.string.personal_data_back)
                )
            }

            Text(
                text = stringResource(R.string.profile_personal_data),
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        ProfileAvatar(
            photoUrl = uiState.photoUrl,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            size = 104.dp
        )

        SectionCard(
            titleRes = R.string.personal_data_information
        ) {
            if (isEditingName) {
                NameEditField(
                    value = editedName,
                    onValueChange = {
                        editedName = it
                        onClearNameUpdateResult()
                    },
                    enabled = !uiState.isSaving
                )

                PrimaryButton(
                    text = stringResource(R.string.personal_data_save),
                    onClick = {
                        onSaveName(editedName)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    enabled = editedName.isNotBlank() &&
                            editedName.trim() != uiState.name,
                    isLoading = uiState.isSaving
                )

                TextButton(
                    onClick = {
                        editedName = uiState.name
                        isEditingName = false
                        onClearNameUpdateResult()
                    },
                    enabled = !uiState.isSaving
                ) {
                    Text(stringResource(R.string.action_cancel))
                }
            } else {
                InfoField(
                    labelRes = R.string.personal_data_name,
                    value = uiState.name
                )

                TextButton(
                    onClick = {
                        editedName = uiState.name
                        isEditingName = true
                        onClearNameUpdateResult()
                    }
                ) {
                    Text(stringResource(R.string.personal_data_edit_name))
                }
            }

            if (uiState.nameUpdateSuccess) {
                Text(
                    text = stringResource(R.string.personal_data_save_success),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            if (uiState.nameUpdateError) {
                Text(
                    text = stringResource(R.string.personal_data_save_error),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.error
                )
            }

            InfoField(
                labelRes = R.string.personal_data_email,
                value = uiState.email
            )

            InfoField(
                labelRes = R.string.personal_data_email_status,
                value = stringResource(
                    if (uiState.isEmailVerified) {
                        R.string.personal_data_verified
                    } else {
                        R.string.personal_data_not_verified
                    }
                )
            )
        }
    }
}

@Preview(name = "Dados pessoais - Claro", showSystemUi = true)
@Composable
private fun PersonalDataScreenLightPreview() {
    SafeBusTheme(darkTheme = false) {
        Surface {
            PersonalDataScreen(
                uiState = ProfileUiState(
                    name = stringResource(R.string.personal_data_preview_name),
                    email = stringResource(R.string.personal_data_preview_email),
                    isEmailVerified = true
                ),
                onBackClick = {},
                onSaveName = {},
                onClearNameUpdateResult = {}
            )
        }
    }
}

@Preview(name = "Dados pessoais - Escuro", showSystemUi = true)
@Composable
private fun PersonalDataScreenDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        Surface {
            PersonalDataScreen(
                uiState = ProfileUiState(
                    name = stringResource(R.string.personal_data_preview_name),
                    email = stringResource(R.string.personal_data_preview_email),
                    isEmailVerified = true
                ),
                onBackClick = {},
                onSaveName = {},
                onClearNameUpdateResult = {}
            )
        }
    }
}