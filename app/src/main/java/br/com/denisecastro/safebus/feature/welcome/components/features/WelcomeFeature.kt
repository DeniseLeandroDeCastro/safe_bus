package br.com.denisecastro.safebus.feature.welcome.components.features

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme
import br.com.denisecastro.safebus.ui.theme.WelcomeCyan

@Composable
fun WelcomeFeature(
    title: String,
    description: String,
    icon: ImageVector,
    accentColor: Color,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(horizontal = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(52.dp)
                .clip(CircleShape)
                .background(accentColor.copy(alpha = 0.12f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = accentColor,
                modifier = Modifier.size(28.dp)
            )
        }

        Text(
            text = title,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center
        )

        Text(
            text = description,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(name = "Funcionalidade - Claro", showBackground = true)
@Composable
private fun WelcomeFeatureLightPreview() {
    SafeBusTheme(darkTheme = false) {
        Surface {
            WelcomeFeature(
                title = "Geolocalização",
                description = "Ajuda mais próxima.",
                icon = Icons.Filled.LocationOn,
                accentColor = WelcomeCyan
            )
        }
    }
}

@Preview(name = "Funcionalidade - Escuro", showBackground = true)
@Composable
private fun WelcomeFeatureDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        Surface {
            WelcomeFeature(
                title = "Geolocalização",
                description = "Ajuda mais próxima.",
                icon = Icons.Filled.LocationOn,
                accentColor = WelcomeCyan
            )
        }
    }
}
