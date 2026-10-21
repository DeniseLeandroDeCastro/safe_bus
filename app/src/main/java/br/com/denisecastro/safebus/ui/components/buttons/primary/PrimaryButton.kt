package br.com.denisecastro.safebus.ui.components.buttons.primary

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isLoading: Boolean = false
) {
    val backgroundColor = MaterialTheme.colorScheme.primary
    val foregroundColor = MaterialTheme.colorScheme.onPrimary

    Button(
        onClick = onClick,
        modifier = modifier.defaultMinSize(minHeight = 52.dp),
        enabled = enabled && !isLoading,
        shape = MaterialTheme.shapes.large,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = foregroundColor,
            disabledContainerColor = if (isLoading) {
                backgroundColor
            } else {
                MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
            },
            disabledContentColor = if (isLoading) {
                foregroundColor
            } else {
                MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
            }
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(18.dp),
                    color = foregroundColor,
                    strokeWidth = 2.dp
                )
            }

            Text(
                text = if (isLoading) "Aguarde..." else text,
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}

@Preview(name = "Primary - Claro", showBackground = true)
@Composable
private fun PrimaryButtonLightPreview() {
    SafeBusTheme(darkTheme = false) {
        PrimaryButton(
            text = "Entrar",
            onClick = {}
        )
    }
}

@Preview(name = "Primary - Escuro", showBackground = true)
@Composable
private fun PrimaryButtonDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        PrimaryButton(
            text = "Entrar",
            onClick = {}
        )
    }
}

@Preview(name = "Primary - Carregando", showBackground = true)
@Composable
private fun PrimaryButtonLoadingPreview() {
    SafeBusTheme(darkTheme = false) {
        PrimaryButton(
            text = "Entrar",
            onClick = {},
            isLoading = true
        )
    }
}

@Preview(name = "Primary - Desabilitado", showBackground = true)
@Composable
private fun PrimaryButtonDisabledPreview() {
    SafeBusTheme(darkTheme = false) {
        PrimaryButton(
            text = "Entrar",
            onClick = {},
            enabled = false
        )
    }
}
