package br.com.denisecastro.safebus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import br.com.denisecastro.safebus.feature.register.screen.RegisterRoute
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

private enum class SafeBusDestination {
    WELCOME,
    REGISTER
}

@Composable
private fun SafeBusApp(
    darkTheme: Boolean = isSystemInDarkTheme()
) {
    var destination by rememberSaveable {
        mutableStateOf(SafeBusDestination.WELCOME)
    }

    BackHandler(
        enabled = destination == SafeBusDestination.REGISTER
    ) {
        destination = SafeBusDestination.WELCOME
    }

    SafeBusTheme(darkTheme = darkTheme) {
        when (destination) {
            SafeBusDestination.WELCOME -> {
                WelcomeScreen(
                    onLoginClick = {
                        // Navegação para LoginScreen será implementada.
                    },
                    onRegisterClick = {
                        destination = SafeBusDestination.REGISTER
                    }
                )
            }

            SafeBusDestination.REGISTER -> {
                RegisterRoute(
                    onBackClick = {
                        destination = SafeBusDestination.WELCOME
                    },
                    onRegisterClick = {
                        // Integração com cadastro será implementada.
                    },
                    onLoginClick = {
                        // Navegação para LoginScreen será implementada.
                    }
                )
            }
        }
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
