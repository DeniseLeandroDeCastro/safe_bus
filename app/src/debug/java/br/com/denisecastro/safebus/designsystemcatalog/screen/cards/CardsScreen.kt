package br.com.denisecastro.safebus.designsystemcatalog.screen.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.denisecastro.safebus.ui.components.cards.StandardCard
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme

@Composable
fun CardsScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "Cards",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = "Componentes utilizados para organizar e apresentar informações nas interfaces do SafeBus.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        CardExample(
            title = "Card padrão",
            description = "Apresentação de informações com espaçamento interno padrão."
        ) {
            StandardCard(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Transporte atual",
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = "Linha 320 - Centro / Bairro",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        CardExample(
            title = "Card com conteúdo personalizado",
            description = "O conteúdo pode ser adaptado conforme a necessidade da tela."
        ) {
            StandardCard(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Dicas de segurança",
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = "Fique atento ao seu entorno durante a viagem.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = "Utilize os recursos de alerta sempre que necessário.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        CardExample(
            title = "Card com espaçamento personalizado",
            description = "Exemplo utilizando contentPadding de 24.dp."
        ) {
            StandardCard(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(24.dp)
            ) {
                Text(
                    text = "Informações da viagem",
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = "Acompanhe as informações do seu transporte.",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
private fun CardExample(
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

@Preview(
    name = "Cards - Tema claro",
    showBackground = true,
    heightDp = 1000
)
@Composable
private fun CardsScreenLightPreview() {
    SafeBusTheme(darkTheme = false) {
        CardsScreen()
    }
}

@Preview(
    name = "Cards - Tema escuro",
    showBackground = true,
    heightDp = 1000
)
@Composable
private fun CardsScreenDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        CardsScreen()
    }
}
