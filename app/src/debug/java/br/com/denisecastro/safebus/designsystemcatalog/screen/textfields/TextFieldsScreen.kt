package br.com.denisecastro.safebus.designsystemcatalog.screen.textfields

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.denisecastro.safebus.feature.welcome.components.fields.StandardTextField
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme

@Composable
fun TextFieldsScreen(
    modifier: Modifier = Modifier
) {
    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier.padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "Campos de texto",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = "Campos reutilizáveis para formulários do SafeBus.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        FieldExample(
            title = "Campo padrão",
            description = "Entrada de texto com ícone e edição habilitada."
        ) {
            StandardTextField(
                value = name,
                onValueChange = { name = it },
                label = "Nome completo",
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = null
                    )
                }
            )
        }

        FieldExample(
            title = "Campo de e-mail",
            description = "Teclado apropriado para endereços de e-mail."
        ) {
            StandardTextField(
                value = email,
                onValueChange = { email = it },
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

        FieldExample(
            title = "Campo de senha",
            description = "Permite mostrar ou ocultar o conteúdo."
        ) {
            StandardTextField(
                value = password,
                onValueChange = { password = it },
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

        FieldExample(
            title = "Campo com erro",
            description = "Apresenta a mensagem de validação."
        ) {
            StandardTextField(
                value = "email-invalido",
                onValueChange = {},
                label = "E-mail",
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

        FieldExample(
            title = "Campo desabilitado",
            description = "Exemplo de campo indisponível para edição."
        ) {
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
}

@Composable
private fun FieldExample(
    title: String,
    description: String,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge
        )

        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        HorizontalDivider(
            color = MaterialTheme.colorScheme.outline.copy(alpha = 0.35f)
        )

        content()
    }
}

@Preview(
    name = "Campos de texto - Claro",
    showBackground = true,
    heightDp = 1100
)
@Composable
private fun TextFieldsScreenLightPreview() {
    SafeBusTheme(darkTheme = false) {
        Surface(color = MaterialTheme.colorScheme.background) {
            TextFieldsScreen()
        }
    }
}

@Preview(
    name = "Campos de texto - Escuro",
    showBackground = true,
    heightDp = 1100
)
@Composable
private fun TextFieldsScreenDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.background) {
            TextFieldsScreen()
        }
    }
}
