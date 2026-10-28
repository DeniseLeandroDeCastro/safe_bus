package br.com.denisecastro.safebus.feature.welcome.components.header

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.denisecastro.safebus.R
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme

@Composable
fun WelcomeHeader(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logotipo SafeBus",
            modifier = Modifier.width(180.dp)
        )
    }
}

@Preview(
    name = "WelcomeHeader - Tema claro",
    showBackground = true
)
@Composable
private fun WelcomeHeaderLightPreview() {
    SafeBusTheme(darkTheme = false) {
        Surface {
            WelcomeHeader(
                modifier = Modifier.padding(vertical = 24.dp)
            )
        }
    }
}

@Preview(
    name = "WelcomeHeader - Tema escuro",
    showBackground = true
)
@Composable
private fun WelcomeHeaderDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        Surface {
            WelcomeHeader(
                modifier = Modifier.padding(vertical = 24.dp)
            )
        }
    }
}
