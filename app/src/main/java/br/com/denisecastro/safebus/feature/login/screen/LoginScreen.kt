package br.com.denisecastro.safebus.feature.login.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.denisecastro.safebus.feature.login.model.LoginUiState
import br.com.denisecastro.safebus.feature.welcome.components.fields.StandardTextField
import br.com.denisecastro.safebus.ui.components.buttons.primary.PrimaryButton
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme

@Composable
fun LoginScreen(
    uiState: LoginUiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onBackClick: () -> Unit,
    onLoginClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onRegisterClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
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
                    contentDescription = "Voltar",
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Entrar",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                text = "Acesse sua conta para continuar sua viagem com o SafeBus.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(8.dp))

            StandardTextField(
                value = uiState.email,
                onValueChange = onEmailChange,
                label = "E-mail",
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
                label = "Senha",
                isPassword = true,
                enabled = !uiState.isLoading,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Lock,
                        contentDescription = null
                    )
                }
            )

            TextButton(
                onClick = onForgotPasswordClick,
                enabled = !uiState.isLoading,
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(
                    text = "Esqueceu sua senha?",
                    style = MaterialTheme.typography.labelLarge
                )
            }

            if (uiState.errorMessage != null) {
                Text(
                    text = uiState.errorMessage,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.error
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            PrimaryButton(
                text = "Entrar",
                onClick = onLoginClick,
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
                    text = "Ainda não tem uma conta?",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )

                TextButton(
                    onClick = onRegisterClick,
                    enabled = !uiState.isLoading
                ) {
                    Text(
                        text = "Criar conta",
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Preview(
    name = "Login - Tema claro",
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun LoginScreenLightPreview() {
    SafeBusTheme(darkTheme = false) {
        LoginScreenPreviewContent()
    }
}

@Preview(
    name = "Login - Tema escuro",
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun LoginScreenDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        LoginScreenPreviewContent()
    }
}

@Composable
private fun LoginScreenPreviewContent() {
    LoginScreen(
        uiState = LoginUiState(),
        onEmailChange = {},
        onPasswordChange = {},
        onBackClick = {},
        onLoginClick = {},
        onForgotPasswordClick = {},
        onRegisterClick = {}
    )
}

@Preview(
    name = "Login - Formulário válido",
    showBackground = true
)
@Composable
private fun LoginScreenValidPreview() {
    SafeBusTheme(darkTheme = false) {
        LoginScreen(
            uiState = LoginUiState(
                email = "ana@email.com",
                password = "Senha123"
            ),
            onEmailChange = {},
            onPasswordChange = {},
            onBackClick = {},
            onLoginClick = {},
            onForgotPasswordClick = {},
            onRegisterClick = {}
        )
    }
}

@Preview(
    name = "Login - Carregando",
    showBackground = true
)
@Composable
private fun LoginScreenLoadingPreview() {
    SafeBusTheme(darkTheme = false) {
        LoginScreen(
            uiState = LoginUiState(
                email = "ana@email.com",
                password = "Senha123",
                isLoading = true
            ),
            onEmailChange = {},
            onPasswordChange = {},
            onBackClick = {},
            onLoginClick = {},
            onForgotPasswordClick = {},
            onRegisterClick = {}
        )
    }
}

@Preview(
    name = "Login - Erro",
    showBackground = true
)
@Composable
private fun LoginScreenErrorPreview() {
    SafeBusTheme(darkTheme = false) {
        LoginScreen(
            uiState = LoginUiState(
                email = "ana@email.com",
                password = "Senha123",
                errorMessage = "Não foi possível entrar. Verifique seus dados."
            ),
            onEmailChange = {},
            onPasswordChange = {},
            onBackClick = {},
            onLoginClick = {},
            onForgotPasswordClick = {},
            onRegisterClick = {}
        )
    }
}
