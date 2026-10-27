package br.com.denisecastro.safebus.feature.home.components.cards

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.denisecastro.safebus.R
import br.com.denisecastro.safebus.ui.theme.AlertDanger
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme
import br.com.denisecastro.safebus.ui.theme.TurquoiseSecondary

@Composable
fun HomeAlertCard(
    title: String,
    description: String,
    @DrawableRes imageRes: Int,
    accentColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2.7f)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 4.dp,
                        end = 2.dp,
                        bottom = 6.dp
                    ),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = description,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Surface(
                    color = accentColor.copy(alpha = 0.12f),
                    shape = CircleShape
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = null,
                        tint = accentColor,
                        modifier = Modifier
                            .padding(6.dp)
                            .size(24.dp)
                    )
                }
            }
        }
    }
}

@Preview(
    name = "Alertas - Tema claro",
    showBackground = true,
    widthDp = 390
)
@Composable
private fun HomeAlertCardLightPreview() {
    SafeBusTheme(darkTheme = false) {
        HomeAlertCardsPreview()
    }
}

@Preview(
    name = "Alertas - Tema escuro",
    showBackground = true,
    widthDp = 390
)
@Composable
private fun HomeAlertCardDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        HomeAlertCardsPreview()
    }
}

@Composable
private fun HomeAlertCardsPreview() {
    Surface(
        color = MaterialTheme.colorScheme.background
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.Top
        ) {
            HomeAlertCard(
                title = "Assalto ou situação de perigo",
                description = "Acione uma viatura próxima.",
                imageRes = R.drawable.alert_danger,
                accentColor = AlertDanger,
                onClick = {},
                modifier = Modifier.weight(1f)
            )

            HomeAlertCard(
                title = "Importunação ou assédio sexual",
                description = "Peça ajuda de forma discreta.",
                imageRes = R.drawable.alert_harassment,
                accentColor = TurquoiseSecondary,
                onClick = {},
                modifier = Modifier.weight(1f)
            )
        }
    }
}
