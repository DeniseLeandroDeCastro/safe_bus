package br.com.denisecastro.safebus.feature.welcome.components.illustrations

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.denisecastro.safebus.R
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme

@Composable
fun WelcomeIllustration(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(
                id = R.drawable.welcome
            ),
            contentDescription = "Ônibus em um ponto de transporte público com passageiros e cidade ao fundo",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Fit
        )
    }
}

@Preview(
    name = "WelcomeIllustration - Tema claro",
    showBackground = true
)
@Composable
private fun WelcomeIllustrationLightPreview() {
    SafeBusTheme(darkTheme = false) {
        WelcomeIllustration(
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(
    name = "WelcomeIllustration - Tema escuro",
    showBackground = true
)
@Composable
private fun WelcomeIllustrationDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        WelcomeIllustration(
            modifier = Modifier.padding(16.dp)
        )
    }
}
