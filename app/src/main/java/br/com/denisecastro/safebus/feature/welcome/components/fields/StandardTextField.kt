package br.com.denisecastro.safebus.feature.welcome.components.fields

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme

@Composable
fun StandardTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPassword: Boolean = false,
    isError: Boolean = false,
    errorMessage: String? = null,
    enabled: Boolean = true,
    leadingIcon: (@Composable () -> Unit)? = null
) {
    var passwordVisible by rememberSaveable {
        mutableStateOf(false)
    }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        label = {
            Text(text = label)
        },
        singleLine = true,
        enabled = enabled,
        isError = isError,
        shape = MaterialTheme.shapes.medium,
        keyboardOptions = KeyboardOptions(
            keyboardType = if (isPassword) {
                KeyboardType.Password
            } else {
                keyboardType
            }
        ),
        visualTransformation = if (isPassword && !passwordVisible) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        leadingIcon = leadingIcon,
        trailingIcon = if (isPassword) {
            {
                IconButton(
                    onClick = {
                        passwordVisible = !passwordVisible
                    }
                ) {
                    Icon(
                        imageVector = if (passwordVisible) {
                            Icons.Filled.VisibilityOff
                        } else {
                            Icons.Filled.Visibility
                        },
                        contentDescription = if (passwordVisible) {
                            "Ocultar senha"
                        } else {
                            "Mostrar senha"
                        }
                    )
                }
            }
        } else {
            null
        },
        supportingText = if (isError && !errorMessage.isNullOrBlank()) {
            {
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error
                )
            }
        } else {
            null
        }
    )
}

@Composable
private fun StandardTextFieldPreviewContainer(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    SafeBusTheme(darkTheme = darkTheme) {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                content()
            }
        }
    }
}

@Preview(
    name = "StandardTextField - Tema claro",
    showBackground = true
)
@Composable
private fun StandardTextFieldLightPreview() {
    StandardTextFieldPreviewContainer {
        StandardTextField(
            value = "",
            onValueChange = {},
            label = "Nome completo",
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = null
                )
            }
        )
    }
}

@Preview(
    name = "StandardTextField - Tema escuro",
    showBackground = true
)
@Composable
private fun StandardTextFieldDarkPreview() {
    StandardTextFieldPreviewContainer(darkTheme = true) {
        StandardTextField(
            value = "",
            onValueChange = {},
            label = "Nome completo",
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = null
                )
            }
        )
    }
}

@Preview(
    name = "StandardTextField - E-mail",
    showBackground = true
)
@Composable
private fun StandardTextFieldEmailPreview() {
    StandardTextFieldPreviewContainer {
        StandardTextField(
            value = "usuario@email.com",
            onValueChange = {},
            label = "E-mail",
            keyboardType = KeyboardType.Email,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Email,
                    contentDescription = null
                )
            }
        )
    }
}

@Preview(
    name = "StandardTextField - Senha",
    showBackground = true
)
@Composable
private fun StandardTextFieldPasswordPreview() {
    StandardTextFieldPreviewContainer {
        StandardTextField(
            value = "12345678",
            onValueChange = {},
            label = "Senha",
            isPassword = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Lock,
                    contentDescription = null
                )
            }
        )
    }
}

@Preview(
    name = "StandardTextField - Erro",
    showBackground = true
)
@Composable
private fun StandardTextFieldErrorPreview() {
    StandardTextFieldPreviewContainer {
        StandardTextField(
            value = "email-invalido",
            onValueChange = {},
            label = "E-mail",
            keyboardType = KeyboardType.Email,
            isError = true,
            errorMessage = "Informe um e-mail válido.",
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Email,
                    contentDescription = null
                )
            }
        )
    }
}

@Preview(
    name = "StandardTextField - Desabilitado",
    showBackground = true
)
@Composable
private fun StandardTextFieldDisabledPreview() {
    StandardTextFieldPreviewContainer {
        StandardTextField(
            value = "Usuário",
            onValueChange = {},
            label = "Nome completo",
            enabled = false,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = null
                )
            }
        )
    }
}
