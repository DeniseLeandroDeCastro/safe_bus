package br.com.denisecastro.safebus.ui.components.buttons.secondary

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme

@Composable
fun SecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isLoading: Boolean = false
) {
    val primaryColor = MaterialTheme.colorScheme.primary
    val isButtonEnabled = enabled && !isLoading

    OutlinedButton(
        onClick = onClick,
        modifier = modifier.defaultMinSize(minHeight = 52.dp),
        enabled = isButtonEnabled,
        shape = MaterialTheme.shapes.large,
        border = BorderStroke(
            width = 1.dp,
            color = when {
                isLoading -> primaryColor
                enabled -> primaryColor
                else -> MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
            }
        ),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = primaryColor,
            disabledContentColor = if (isLoading) {
                primaryColor
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
                    color = primaryColor,
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

@Preview(name = "Secondary - Claro", showBackground = true)
@Composable
private fun SecondaryButtonLightPreview() {
    SafeBusTheme(darkTheme = false) {
        SecondaryButton(
            text = "Criar conta",
            onClick = {}
        )
    }
}

@Preview(name = "Secondary - Escuro", showBackground = true)
@Composable
private fun SecondaryButtonDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        SecondaryButton(
            text = "Criar conta",
            onClick = {}
        )
    }
}

@Preview(name = "Secondary - Carregando", showBackground = true)
@Composable
private fun SecondaryButtonLoadingPreview() {
    SafeBusTheme(darkTheme = false) {
        SecondaryButton(
            text = "Criar conta",
            onClick = {},
            isLoading = true
        )
    }
}

@Preview(name = "Secondary - Desabilitado", showBackground = true)
@Composable
private fun SecondaryButtonDisabledPreview() {
    SafeBusTheme(darkTheme = false) {
        SecondaryButton(
            text = "Criar conta",
            onClick = {},
            enabled = false
        )
    }
}
