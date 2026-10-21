package br.com.denisecastro.safebus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import br.com.denisecastro.safebus.feature.welcome.screen.WelcomeScreen
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            SafeBusApp()
        }
    }
}

@Composable
private fun SafeBusApp(
    darkTheme: Boolean = androidx.compose.foundation.isSystemInDarkTheme()
) {
    SafeBusTheme(darkTheme = darkTheme) {
        WelcomeScreen(
            onLoginClick = {
                // Navegação para LoginScreen será implementada.
            },
            onRegisterClick = {
                // Navegação para RegisterScreen será implementada.
            }
        )
    }
}

@Preview(
    name = "SafeBus - Tema claro",
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun SafeBusAppLightPreview() {
    SafeBusApp(darkTheme = false)
}

@Preview(
    name = "SafeBus - Tema escuro",
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun SafeBusAppDarkPreview() {
    SafeBusApp(darkTheme = true)
}
