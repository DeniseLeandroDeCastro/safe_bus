package br.com.denisecastro.safebus.designsystemcatalog.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.denisecastro.safebus.designsystemcatalog.screen.CatalogScreen
import br.com.denisecastro.safebus.designsystemcatalog.screen.placeholder.CatalogPlaceholderScreen
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme
import br.com.denisecastro.safebus.designsystemcatalog.screen.colors.ColorsScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogNavigation(
    isDarkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit
) {
    var destination by rememberSaveable {
        mutableStateOf(CatalogDestination.HOME)
    }

    BackHandler(enabled = destination != CatalogDestination.HOME) {
        destination = CatalogDestination.HOME
    }

    if (destination == CatalogDestination.HOME) {
        CatalogScreen(
            isDarkTheme = isDarkTheme,
            onThemeChange = onThemeChange,
            onColorsClick = {
                destination = CatalogDestination.COLORS
            },
            onTypographyClick = {
                destination = CatalogDestination.TYPOGRAPHY
            },
            onButtonsClick = {
                destination = CatalogDestination.BUTTONS
            },
            onCardsClick = {
                destination = CatalogDestination.CARDS
            }
        )
    } else {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = MaterialTheme.colorScheme.background,
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = destination.title)
                    },
                    navigationIcon = {
                        TextButton(
                            onClick = {
                                destination = CatalogDestination.HOME
                            }
                        ) {
                            Text("Voltar")
                        }
                    }
                )
            }
        ) { innerPadding ->
            when (destination) {
                CatalogDestination.COLORS -> {
                    ColorsScreen(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(innerPadding)
                    )
                }

                else -> {
                    CatalogPlaceholderScreen(
                        title = destination.title,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Preview(
    name = "Navegação - Claro",
    showBackground = true
)
@Composable
private fun CatalogNavigationLightPreview() {
    SafeBusTheme(darkTheme = false) {
        CatalogNavigation(
            isDarkTheme = false,
            onThemeChange = {}
        )
    }
}

@Preview(
    name = "Navegação - Escuro",
    showBackground = true
)
@Composable
private fun CatalogNavigationDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        CatalogNavigation(
            isDarkTheme = true,
            onThemeChange = {}
        )
    }
}
