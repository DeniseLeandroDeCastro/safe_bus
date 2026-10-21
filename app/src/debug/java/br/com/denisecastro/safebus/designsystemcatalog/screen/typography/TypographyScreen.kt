package br.com.denisecastro.safebus.designsystemcatalog.screen.typography

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme

@Composable
fun TypographyScreen(
    modifier: Modifier = Modifier
) {
    val typography = MaterialTheme.typography

    Column(
        modifier = modifier.padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "Tipografia",
            style = typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = "Estilos tipográficos utilizados nas interfaces do SafeBus.",
            style = typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        TypographySample(
            name = "Headline Large",
            example = "Título principal",
            style = typography.headlineLarge
        )

        TypographySample(
            name = "Headline Medium",
            example = "Título de seção",
            style = typography.headlineMedium
        )

        TypographySample(
            name = "Title Large",
            example = "Título de destaque",
            style = typography.titleLarge
        )

        TypographySample(
            name = "Title Medium",
            example = "Título de componente",
            style = typography.titleMedium
        )

        TypographySample(
            name = "Body Large",
            example = "Texto principal da interface.",
            style = typography.bodyLarge
        )

        TypographySample(
            name = "Body Medium",
            example = "Texto auxiliar e descrições.",
            style = typography.bodyMedium
        )

        TypographySample(
            name = "Label Large",
            example = "Rótulo de botão",
            style = typography.labelLarge
        )

        TypographySample(
            name = "Label Medium",
            example = "Rótulo secundário",
            style = typography.labelMedium
        )
    }
}

@Composable
private fun TypographySample(
    name: String,
    example: String,
    style: TextStyle
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = example,
            style = style,
            color = MaterialTheme.colorScheme.onBackground
        )

        Text(
            text = buildString {
                append("Fonte: ${style.fontSize}")
                append("  •  Linha: ${style.lineHeight}")
                append("  •  Peso: ${style.fontWeight.toDisplayName()}")
            },
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        HorizontalDivider(
            color = MaterialTheme.colorScheme.outline.copy(alpha = 0.35f)
        )
    }
}

private fun FontWeight?.toDisplayName(): String {
    return when (this) {
        FontWeight.Bold -> "Bold"
        FontWeight.SemiBold -> "SemiBold"
        FontWeight.Medium -> "Medium"
        FontWeight.Normal -> "Normal"
        else -> this?.weight?.toString() ?: "Padrão"
    }
}

@Preview(
    name = "Tipografia - Tema claro",
    showBackground = true,
    heightDp = 1000
)
@Composable
private fun TypographyScreenLightPreview() {
    SafeBusTheme(darkTheme = false) {
        TypographyScreen()
    }
}

@Preview(
    name = "Tipografia - Tema escuro",
    showBackground = true,
    heightDp = 1000
)
@Composable
private fun TypographyScreenDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        TypographyScreen()
    }
}
