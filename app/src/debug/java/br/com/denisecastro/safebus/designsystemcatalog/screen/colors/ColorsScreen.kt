package br.com.denisecastro.safebus.designsystemcatalog.screen.colors

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.denisecastro.safebus.ui.theme.AlertDanger
import br.com.denisecastro.safebus.ui.theme.AlertHarassment
import br.com.denisecastro.safebus.ui.theme.BluePrimary
import br.com.denisecastro.safebus.ui.theme.BorderColor
import br.com.denisecastro.safebus.ui.theme.DarkBackground
import br.com.denisecastro.safebus.ui.theme.DarkOnBackground
import br.com.denisecastro.safebus.ui.theme.DarkPrimary
import br.com.denisecastro.safebus.ui.theme.DarkSurface
import br.com.denisecastro.safebus.ui.theme.Error
import br.com.denisecastro.safebus.ui.theme.LightBackground
import br.com.denisecastro.safebus.ui.theme.LightOnBackground
import br.com.denisecastro.safebus.ui.theme.LightSurface
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme
import br.com.denisecastro.safebus.ui.theme.Success
import br.com.denisecastro.safebus.ui.theme.TextSecondary
import br.com.denisecastro.safebus.ui.theme.TurquoiseSecondary
import br.com.denisecastro.safebus.ui.theme.Warning
import java.util.Locale

@Composable
fun ColorsScreen(
    modifier: Modifier = Modifier
) {
    val colors = MaterialTheme.colorScheme

    Column(
        modifier = modifier.padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = "Paleta de cores",
            style = MaterialTheme.typography.headlineMedium,
            color = colors.primary
        )

        Text(
            text = "Cores utilizadas na identidade visual e nos componentes do SafeBus.",
            style = MaterialTheme.typography.bodyMedium,
            color = colors.onSurfaceVariant
        )

        ColorSection(title = "Cores do tema atual") {
            ColorSample("Primary", colors.primary)
            ColorSample("On Primary", colors.onPrimary)
            ColorSample("Primary Container", colors.primaryContainer)
            ColorSample("Secondary", colors.secondary)
            ColorSample("On Secondary", colors.onSecondary)
            ColorSample("Secondary Container", colors.secondaryContainer)
            ColorSample("Background", colors.background)
            ColorSample("Surface", colors.surface)
            ColorSample("Surface Variant", colors.surfaceVariant)
            ColorSample("On Background", colors.onBackground)
            ColorSample("On Surface", colors.onSurface)
            ColorSample("On Surface Variant", colors.onSurfaceVariant)
            ColorSample("Outline", colors.outline)
            ColorSample("Error", colors.error)
        }

        ColorSection(title = "Identidade visual") {
            ColorSample("Blue Primary", BluePrimary)
            ColorSample("Turquoise Secondary", TurquoiseSecondary)
        }

        ColorSection(title = "Tema claro") {
            ColorSample("Light Background", LightBackground)
            ColorSample("Light Surface", LightSurface)
            ColorSample("Light On Background", LightOnBackground)
        }

        ColorSection(title = "Tema escuro") {
            ColorSample("Dark Primary", DarkPrimary)
            ColorSample("Dark Background", DarkBackground)
            ColorSample("Dark Surface", DarkSurface)
            ColorSample("Dark On Background", DarkOnBackground)
        }

        ColorSection(title = "Alertas") {
            ColorSample("Alert Danger", AlertDanger)
            ColorSample("Alert Harassment", AlertHarassment)
        }

        ColorSection(title = "Feedback") {
            ColorSample("Success", Success)
            ColorSample("Warning", Warning)
            ColorSample("Error", Error)
        }

        ColorSection(title = "Cores neutras") {
            ColorSample("Text Secondary", TextSecondary)
            ColorSample("Border Color", BorderColor)
        }
    }
}

@Composable
private fun ColorSection(
    title: String,
    content: @Composable () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
        )

        HorizontalDivider(
            color = MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)
        )

        content()
    }
}

@Composable
private fun ColorSample(
    name: String,
    color: Color
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Spacer(
            modifier = Modifier
                .size(52.dp)
                .background(
                    color = color,
                    shape = RoundedCornerShape(12.dp)
                )
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outline,
                    shape = RoundedCornerShape(12.dp)
                )
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = color.toHexString(),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

private fun Color.toHexString(): String {
    return String.format(
        Locale.US,
        "#%06X",
        toArgb() and 0xFFFFFF
    )
}

@Preview(
    name = "Cores - Tema claro",
    showBackground = true,
    heightDp = 900
)
@Composable
private fun ColorsScreenLightPreview() {
    SafeBusTheme(darkTheme = false) {
        ColorsScreen()
    }
}

@Preview(
    name = "Cores - Tema escuro",
    showBackground = true,
    heightDp = 900
)
@Composable
private fun ColorsScreenDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        ColorsScreen()
    }
}
