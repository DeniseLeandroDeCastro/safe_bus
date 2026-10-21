package br.com.denisecastro.safebus.designsystemcatalog.screen.buttons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.denisecastro.safebus.ui.components.buttons.primary.PrimaryButton
import br.com.denisecastro.safebus.ui.components.buttons.secondary.SecondaryButton
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme

@Composable
fun ButtonsScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "Botões",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = "Componentes de ação utilizados nas interfaces do SafeBus.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        ButtonSection(
            title = "PrimaryButton",
            description = "Botão principal, utilizado para ações de maior destaque."
        ) {
            ButtonExample(label = "Estado normal") {
                PrimaryButton(
                    text = "Entrar",
                    onClick = {},
                    modifier = Modifier.fillMaxWidth()
                )
            }

            ButtonExample(label = "Estado desabilitado") {
                PrimaryButton(
                    text = "Entrar",
                    onClick = {},
                    modifier = Modifier.fillMaxWidth(),
                    enabled = false
                )
            }

            ButtonExample(label = "Estado carregando") {
                PrimaryButton(
                    text = "Entrar",
                    onClick = {},
                    modifier = Modifier.fillMaxWidth(),
                    isLoading = true
                )
            }
        }

        ButtonSection(
            title = "SecondaryButton",
            description = "Botão secundário, utilizado para ações alternativas."
        ) {
            ButtonExample(label = "Estado normal") {
                SecondaryButton(
                    text = "Criar conta",
                    onClick = {},
                    modifier = Modifier.fillMaxWidth()
                )
            }

            ButtonExample(label = "Estado desabilitado") {
                SecondaryButton(
                    text = "Criar conta",
                    onClick = {},
                    modifier = Modifier.fillMaxWidth(),
                    enabled = false
                )
            }

            ButtonExample(label = "Estado carregando") {
                SecondaryButton(
                    text = "Criar conta",
                    onClick = {},
                    modifier = Modifier.fillMaxWidth(),
                    isLoading = true
                )
            }
        }
    }
}

@Composable
private fun ButtonSection(
    title: String,
    description: String,
    content: @Composable () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
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

@Composable
private fun ButtonExample(
    label: String,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        content()
    }
}

@Preview(
    name = "Botões - Tema claro",
    showBackground = true,
    heightDp = 1000
)
@Composable
private fun ButtonsScreenLightPreview() {
    SafeBusTheme(darkTheme = false) {
        ButtonsScreen()
    }
}

@Preview(
    name = "Botões - Tema escuro",
    showBackground = true,
    heightDp = 1000
)
@Composable
private fun ButtonsScreenDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        ButtonsScreen()
    }
}
