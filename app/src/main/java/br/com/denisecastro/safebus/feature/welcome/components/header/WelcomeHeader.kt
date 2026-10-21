package br.com.denisecastro.safebus.feature.welcome.components.header

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.denisecastro.safebus.R
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme

@Composable
fun WelcomeHeader(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Image(
            painter = painterResource(
                id = R.drawable.logo
            ),
            contentDescription = "Logotipo SafeBus",
            modifier = Modifier.width(180.dp)
        )

        Text(
            text = "Transporte público mais seguro para todos.",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
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
        WelcomeHeader(
            modifier = Modifier.padding(vertical = 24.dp)
        )
    }
}

@Preview(
    name = "WelcomeHeader - Tema escuro",
    showBackground = true
)
@Composable
private fun WelcomeHeaderDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        WelcomeHeader(
            modifier = Modifier.padding(vertical = 24.dp)
        )
    }
}
