package br.com.denisecastro.safebus.feature.home.components.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.denisecastro.safebus.R
import br.com.denisecastro.safebus.ui.theme.HomeHeroBackground
import br.com.denisecastro.safebus.ui.theme.HomeHeroHighlight
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme

@Composable
fun TravelSafetyCard(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(224.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(HomeHeroBackground)
    ) {
        Image(
            painter = painterResource(R.drawable.home_hero_art),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(0.68f)
                .fillMaxSize()
                .align(Alignment.CenterEnd),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            HomeHeroBackground,
                            HomeHeroBackground.copy(alpha = 0.98f),
                            HomeHeroBackground.copy(alpha = 0.65f),
                            Color.Transparent
                        )
                    )
                )
        )

        Column(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .width(220.dp)
                .padding(start = 20.dp, end = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Column {
                Text(
                    text = "Viaje mais",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Text(
                    text = "segura",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.ExtraBold,
                    color = HomeHeroHighlight
                )
            }

            Text(
                text = "Em caso de emergência, acesse um dos alertas abaixo.",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )
        }
    }
}

@Preview(
    name = "Viagem segura - Claro",
    showBackground = true,
    widthDp = 390
)
@Composable
private fun TravelSafetyCardLightPreview() {
    SafeBusTheme(darkTheme = false) {
        Box(Modifier.padding(16.dp)) {
            TravelSafetyCard()
        }
    }
}

@Preview(
    name = "Viagem segura - Escuro",
    showBackground = true,
    widthDp = 390
)
@Composable
private fun TravelSafetyCardDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        Box(Modifier.padding(16.dp)) {
            TravelSafetyCard()
        }
    }
}
