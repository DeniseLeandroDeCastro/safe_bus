package br.com.denisecastro.safebus.designsystemcatalog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import br.com.denisecastro.safebus.designsystemcatalog.screen.CatalogScreen
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme

class CatalogActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            CatalogApp()
        }
    }
}

@Composable
private fun CatalogApp() {
    val systemDarkTheme = isSystemInDarkTheme()

    var isDarkTheme by rememberSaveable {
        mutableStateOf(systemDarkTheme)
    }

    SafeBusTheme(darkTheme = isDarkTheme) {
        CatalogScreen(
            isDarkTheme = isDarkTheme,
            onThemeChange = { isDarkTheme = it },
            onColorsClick = {},
            onTypographyClick = {},
            onButtonsClick = {},
            onCardsClick = {}
        )
    }
}


@Preview(
    name = "CatalogActivity - Tema claro",
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun CatalogActivityLightPreview() {
    SafeBusTheme(darkTheme = false) {
        CatalogScreen(
            isDarkTheme = false,
            onThemeChange = {},
            onColorsClick = {},
            onTypographyClick = {},
            onButtonsClick = {},
            onCardsClick = {}
        )
    }
}

@Preview(
    name = "CatalogActivity - Tema escuro",
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun CatalogActivityDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        CatalogScreen(
            isDarkTheme = true,
            onThemeChange = {},
            onColorsClick = {},
            onTypographyClick = {},
            onButtonsClick = {},
            onCardsClick = {}
        )
    }
}

