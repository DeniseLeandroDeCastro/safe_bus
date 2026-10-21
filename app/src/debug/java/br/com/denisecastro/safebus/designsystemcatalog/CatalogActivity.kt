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
import br.com.denisecastro.safebus.designsystemcatalog.navigation.CatalogNavigation
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
private fun CatalogApp(
    initialDarkTheme: Boolean = isSystemInDarkTheme()
) {
    var isDarkTheme by rememberSaveable {
        mutableStateOf(initialDarkTheme)
    }

    SafeBusTheme(darkTheme = isDarkTheme) {
        CatalogNavigation(
            isDarkTheme = isDarkTheme,
            onThemeChange = { isDarkTheme = it }
        )
    }
}

@Preview(
    name = "CatalogActivity - Claro",
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun CatalogActivityLightPreview() {
    CatalogApp(initialDarkTheme = false)
}

@Preview(
    name = "CatalogActivity - Escuro",
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun CatalogActivityDarkPreview() {
    CatalogApp(initialDarkTheme = true)
}
