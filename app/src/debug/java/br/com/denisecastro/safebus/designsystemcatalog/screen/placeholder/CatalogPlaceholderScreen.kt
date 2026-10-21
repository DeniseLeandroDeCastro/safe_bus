package br.com.denisecastro.safebus.designsystemcatalog.screen.placeholder

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme

@Composable
fun CatalogPlaceholderScreen(
    title: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = "Componentes em desenvolvimento.",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Preview(
    name = "Placeholder - Tema claro",
    showBackground = true
)
@Composable
private fun CatalogPlaceholderLightPreview() {
    SafeBusTheme(darkTheme = false) {
        CatalogPlaceholderScreen(
            title = "Cores"
        )
    }
}

@Preview(
    name = "Placeholder - Tema escuro",
    showBackground = true
)
@Composable
private fun CatalogPlaceholderDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        CatalogPlaceholderScreen(
            title = "Cores"
        )
    }
}
