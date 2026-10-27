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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import br.com.denisecastro.safebus.ui.theme.HomeTipsAccent
import br.com.denisecastro.safebus.ui.theme.HomeTipsBackground
import br.com.denisecastro.safebus.ui.theme.HomeTipsTitle
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme

@Composable
fun SafetyTipsCard(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(188.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(HomeTipsBackground)
    ) {
        Image(
            painter = painterResource(R.drawable.home_tips_art),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(0.64f)
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
                            HomeTipsBackground,
                            HomeTipsBackground.copy(alpha = 0.96f),
                            HomeTipsBackground.copy(alpha = 0.50f),
                            Color.Transparent
                        )
                    )
                )
        )

        Column(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .width(228.dp)
                .padding(start = 18.dp, end = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Dicas de segurança",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = HomeTipsTitle
            )

            Text(
                text = "Confira orientações para uma viagem mais segura.",
                style = MaterialTheme.typography.bodySmall,
                color = HomeTipsTitle
            )

            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = HomeTipsAccent,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(50)
            ) {
                Text("Ver dicas")

                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}

@Preview(
    name = "Dicas - Claro",
    showBackground = true,
    widthDp = 390
)
@Composable
private fun SafetyTipsCardLightPreview() {
    SafeBusTheme(darkTheme = false) {
        Box(Modifier.padding(16.dp)) {
            SafetyTipsCard(onClick = {})
        }
    }
}

@Preview(
    name = "Dicas - Escuro",
    showBackground = true,
    widthDp = 390
)
@Composable
private fun SafetyTipsCardDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        Box(Modifier.padding(16.dp)) {
            SafetyTipsCard(onClick = {})
        }
    }
}
