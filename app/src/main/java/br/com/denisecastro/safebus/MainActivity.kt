package br.com.denisecastro.safebus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import br.com.denisecastro.safebus.navigation.SafeBusNavHost
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            SafeBusTheme {
                SafeBusNavHost()
            }
        }
    }
}
