package br.com.denisecastro.safebus.feature.login.components.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.denisecastro.safebus.feature.welcome.components.fields.StandardTextField
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme

@Composable
fun ForgotPasswordDialog(
    initialEmail: String,
    isLoading: Boolean,
    message: String?,
    isSuccess: Boolean,
    onDismiss: () -> Unit,
    onSendClick: (String) -> Unit
) {
    var email by remember(initialEmail) {
        mutableStateOf(initialEmail)
    }

    AlertDialog(
        onDismissRequest = {
            if (!isLoading) onDismiss()
        },
        title = {
            Text(
                text = if (isSuccess) {
                    "Solicitação recebida"
                } else {
                    "Recuperar senha"
                }
            )
        },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (!isSuccess) {
                    Text(
                        text = "Informe seu e-mail para receber " +
                                "um link de recuperação de senha.",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    StandardTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = "E-mail",
                        keyboardType = KeyboardType.Email,
                        enabled = !isLoading,
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Email,
                                contentDescription = null
                            )
                        }
                    )
                }

                if (message != null) {
                    Text(
                        text = message,
                        style = MaterialTheme.typography.bodyMedium,
                        color = if (isSuccess) {
                            MaterialTheme.colorScheme.onSurface
                        } else {
                            MaterialTheme.colorScheme.error
                        }
                    )
                }
            }
        },
        confirmButton = {
            if (isSuccess) {
                TextButton(onClick = onDismiss) {
                    Text("Concluir")
                }
            } else {
                TextButton(
                    onClick = { onSendClick(email.trim()) },
                    enabled = email.isNotBlank() && !isLoading
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.fillMaxWidth(0.08f),
                            strokeWidth = 2.dp
                        )
                    } else {
                        Text("Enviar link")
                    }
                }
            }
        },
        dismissButton = {
            if (!isSuccess) {
                TextButton(
                    onClick = onDismiss,
                    enabled = !isLoading
                ) {
                    Text("Cancelar")
                }
            }
        }
    )
}

@Preview(name = "Recuperação - Claro", showSystemUi = true)
@Composable
private fun ForgotPasswordDialogLightPreview() {
    SafeBusTheme(darkTheme = false) {
        ForgotPasswordDialog(
            initialEmail = "ana@email.com",
            isLoading = false,
            message = null,
            isSuccess = false,
            onDismiss = {},
            onSendClick = {}
        )
    }
}

@Preview(name = "Recuperação - Escuro", showSystemUi = true)
@Composable
private fun ForgotPasswordDialogDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        ForgotPasswordDialog(
            initialEmail = "",
            isLoading = false,
            message = null,
            isSuccess = false,
            onDismiss = {},
            onSendClick = {}
        )
    }
}

@Preview(name = "Recuperação - Sucesso", showSystemUi = true)
@Composable
private fun ForgotPasswordDialogSuccessPreview() {
    SafeBusTheme(darkTheme = false) {
        ForgotPasswordDialog(
            initialEmail = "ana@email.com",
            isLoading = false,
            message = "Se o e-mail estiver cadastrado, você receberá " +
                    "um link para redefinir sua senha.",
            isSuccess = true,
            onDismiss = {},
            onSendClick = {}
        )
    }
}


