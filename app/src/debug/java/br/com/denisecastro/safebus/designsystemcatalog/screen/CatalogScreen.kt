package br.com.denisecastro.safebus.designsystemcatalog.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme

@Composable
fun CatalogScreen(
    isDarkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit,
    onColorsClick: () -> Unit,
    onTypographyClick: () -> Unit,
    onButtonsClick: () -> Unit,
    onCardsClick: () -> Unit,
    onTextFieldsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "SafeBus Design System",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "Catálogo de componentes visuais",
                style = MaterialTheme.typography.bodyLarge
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Tema escuro",
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(modifier = Modifier.weight(1f))

                Switch(
                    checked = isDarkTheme,
                    onCheckedChange = onThemeChange
                )
            }

            HorizontalDivider()

            CatalogItem(
                title = "Cores",
                description = "Paleta e cores semânticas",
                onClick = onColorsClick
            )

            CatalogItem(
                title = "Tipografia",
                description = "Títulos, textos e rótulos",
                onClick = onTypographyClick
            )

            CatalogItem(
                title = "Botões",
                description = "Primário, secundário e estados",
                onClick = onButtonsClick
            )

            CatalogItem(
                title = "Cards",
                description = "Componentes de apresentação",
                onClick = onCardsClick
            )

            CatalogItem(
                title = "Campos de texto",
                description = "Campos, senhas e estados de validação",
                onClick = onTextFieldsClick
            )
        }
    }
}

@Composable
private fun CatalogItem(
    title: String,
    description: String,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }

    HorizontalDivider(
        color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
    )
}

@Preview(
    name = "Catálogo - Claro",
    showBackground = true
)
@Composable
private fun CatalogScreenLightPreview() {
    SafeBusTheme(darkTheme = false) {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            CatalogScreen(
                isDarkTheme = false,
                onThemeChange = {},
                onColorsClick = {},
                onTypographyClick = {},
                onButtonsClick = {},
                onCardsClick = {},
                onTextFieldsClick = {}
            )
        }
    }
}

@Preview(
    name = "Catálogo - Escuro",
    showBackground = true
)
@Composable
private fun CatalogScreenDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            CatalogScreen(
                isDarkTheme = true,
                onThemeChange = {},
                onColorsClick = {},
                onTypographyClick = {},
                onButtonsClick = {},
                onCardsClick = {},
                onTextFieldsClick = {}
            )
        }
    }
}
