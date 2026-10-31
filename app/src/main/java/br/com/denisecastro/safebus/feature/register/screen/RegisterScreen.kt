package br.com.denisecastro.safebus.feature.register.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.denisecastro.safebus.R
import br.com.denisecastro.safebus.feature.register.model.RegisterUiState
import br.com.denisecastro.safebus.feature.welcome.components.fields.StandardTextField
import br.com.denisecastro.safebus.ui.components.buttons.primary.PrimaryButton
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme

@Composable
fun RegisterScreen(
    uiState: RegisterUiState,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onBackClick: () -> Unit,
    onRegisterClick: () -> Unit,
    onLoginClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.navigationBars)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            IconButton(
                onClick = onBackClick,
                enabled = !uiState.isLoading
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.register_back),
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(R.string.register_title),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                text = stringResource(R.string.register_description),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(8.dp))

            StandardTextField(
                value = uiState.name,
                onValueChange = onNameChange,
                label = stringResource(R.string.register_name),
                enabled = !uiState.isLoading,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = null
                    )
                }
            )

            StandardTextField(
                value = uiState.email,
                onValueChange = onEmailChange,
                label = stringResource(R.string.register_email),
                keyboardType = KeyboardType.Email,
                enabled = !uiState.isLoading,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Email,
                        contentDescription = null
                    )
                }
            )

            StandardTextField(
                value = uiState.password,
                onValueChange = onPasswordChange,
                label = stringResource(R.string.register_password),
                isPassword = true,
                enabled = !uiState.isLoading,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Lock,
                        contentDescription = null
                    )
                }
            )

            StandardTextField(
                value = uiState.confirmPassword,
                onValueChange = onConfirmPasswordChange,
                label = stringResource(R.string.register_confirm_password),
                isPassword = true,
                enabled = !uiState.isLoading,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Lock,
                        contentDescription = null
                    )
                }
            )

            Spacer(modifier = Modifier.height(12.dp))

            uiState.errorMessage?.let { message ->
                Text(
                    text = message,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }

            PrimaryButton(
                text = stringResource(R.string.register_title),
                onClick = onRegisterClick,
                modifier = Modifier.fillMaxWidth(),
                enabled = uiState.isFormValid && !uiState.isLoading,
                isLoading = uiState.isLoading
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.register_has_account),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )

                TextButton(
                    onClick = onLoginClick,
                    enabled = !uiState.isLoading
                ) {
                    Text(
                        text = stringResource(R.string.register_login),
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = stringResource(R.string.register_terms),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Preview(name = "Cadastro - Tema claro", showSystemUi = true)
@Composable
private fun RegisterScreenLightPreview() {
    SafeBusTheme(darkTheme = false) {
        RegisterScreen(
            uiState = RegisterUiState(),
            onNameChange = {},
            onEmailChange = {},
            onPasswordChange = {},
            onConfirmPasswordChange = {},
            onBackClick = {},
            onRegisterClick = {},
            onLoginClick = {}
        )
    }
}

@Preview(name = "Cadastro - Tema escuro", showSystemUi = true)
@Composable
private fun RegisterScreenDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        RegisterScreen(
            uiState = RegisterUiState(),
            onNameChange = {},
            onEmailChange = {},
            onPasswordChange = {},
            onConfirmPasswordChange = {},
            onBackClick = {},
            onRegisterClick = {},
            onLoginClick = {}
        )
    }
}

@Preview(name = "Cadastro - Formulário válido", showSystemUi = true)
@Composable
private fun RegisterScreenValidPreview() {
    SafeBusTheme(darkTheme = false) {
        RegisterScreen(
            uiState = RegisterUiState(
                name = "Ana",
                email = "ana@email.com",
                password = "Senha123",
                confirmPassword = "Senha123",
                isFormValid = true
            ),
            onNameChange = {},
            onEmailChange = {},
            onPasswordChange = {},
            onConfirmPasswordChange = {},
            onBackClick = {},
            onRegisterClick = {},
            onLoginClick = {}
        )
    }
}

@Preview(name = "Cadastro - Carregando", showSystemUi = true)
@Composable
private fun RegisterScreenLoadingPreview() {
    SafeBusTheme(darkTheme = false) {
        RegisterScreen(
            uiState = RegisterUiState(
                name = "Ana",
                email = "ana@email.com",
                password = "Senha123",
                confirmPassword = "Senha123",
                isFormValid = true,
                isLoading = true
            ),
            onNameChange = {},
            onEmailChange = {},
            onPasswordChange = {},
            onConfirmPasswordChange = {},
            onBackClick = {},
            onRegisterClick = {},
            onLoginClick = {}
        )
    }
}

@Preview(name = "Cadastro - Erro", showSystemUi = true)
@Composable
private fun RegisterScreenErrorPreview() {
    SafeBusTheme(darkTheme = false) {
        RegisterScreen(
            uiState = RegisterUiState(
                name = "Ana",
                email = "ana@email.com",
                password = "Senha123",
                confirmPassword = "Senha123",
                isFormValid = true,
                errorMessage = stringResource(R.string.register_preview_error)
            ),
            onNameChange = {},
            onEmailChange = {},
            onPasswordChange = {},
            onConfirmPasswordChange = {},
            onBackClick = {},
            onRegisterClick = {},
            onLoginClick = {}
        )
    }
}
