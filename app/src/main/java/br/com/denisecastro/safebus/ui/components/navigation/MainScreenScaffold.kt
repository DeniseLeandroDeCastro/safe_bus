package br.com.denisecastro.safebus.ui.components.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme

@Composable
fun MainScreenScaffold(
    selectedDestination: SafeBusBottomDestination,
    onDestinationClick: (SafeBusBottomDestination) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier,
        bottomBar = {
            SafeBusBottomBar(
                selectedDestination = selectedDestination,
                onDestinationClick = onDestinationClick
            )
        },
        content = content
    )
}

@Preview(
    name = "Navegação - Tema claro",
    showBackground = true
)
@Composable
private fun MainScreenScaffoldLightPreview() {
    SafeBusTheme(darkTheme = false) {
        Surface {
            MainScreenScaffold(
                selectedDestination = SafeBusBottomDestination.HOME,
                onDestinationClick = {}
            ) { }
        }
    }
}

@Preview(
    name = "Navegação - Tema escuro",
    showBackground = true
)
@Composable
private fun MainScreenScaffoldDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        Surface {
            MainScreenScaffold(
                selectedDestination = SafeBusBottomDestination.HOME,
                onDestinationClick = {}
            ) { }
        }
    }
}
