package br.com.denisecastro.safebus.feature.home.components.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme

@Composable
fun HomeAlertCard(
    title: String,
    icon: ImageVector,
    backgroundColor: Color,
    accentColor: Color,
    contentColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = CircleShape,
                color = accentColor,
                modifier = Modifier.size(64.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(36.dp)
                    )
                }
            }

            Text(
                text = title,
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = contentColor
            )

            Surface(
                shape = CircleShape,
                color = accentColor,
                modifier = Modifier.size(36.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}

@Preview(
    name = "Alerta de perigo - Tema claro",
    showBackground = true
)
@Composable
private fun DangerAlertCardLightPreview() {
    SafeBusTheme(darkTheme = false) {
        Surface {
            HomeAlertCard(
                title = "Assalto ou situação de perigo",
                icon = Icons.Filled.DirectionsBus,
                backgroundColor = Color(0xFFFFE7E5),
                accentColor = Color(0xFFFF302A),
                contentColor = Color(0xFF5A1010),
                onClick = {},
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Preview(
    name = "Alerta de perigo - Tema escuro",
    showBackground = true
)
@Composable
private fun DangerAlertCardDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        Surface {
            HomeAlertCard(
                title = "Assalto ou situação de perigo",
                icon = Icons.Filled.DirectionsBus,
                backgroundColor = Color(0xFF402020),
                accentColor = Color(0xFFFF453A),
                contentColor = Color.White,
                onClick = {},
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Preview(
    name = "Alerta de importunação - Tema claro",
    showBackground = true
)
@Composable
private fun HarassmentAlertCardLightPreview() {
    SafeBusTheme(darkTheme = false) {
        Surface {
            HomeAlertCard(
                title = "Importunação ou assédio sexual",
                icon = Icons.Filled.Person,
                backgroundColor = Color(0xFFECE6FF),
                accentColor = Color(0xFF6842F5),
                contentColor = Color(0xFF25145B),
                onClick = {},
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Preview(
    name = "Alerta de importunação - Tema escuro",
    showBackground = true
)
@Composable
private fun HarassmentAlertCardDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        Surface {
            HomeAlertCard(
                title = "Importunação ou assédio sexual",
                icon = Icons.Filled.Person,
                backgroundColor = Color(0xFF302448),
                accentColor = Color(0xFF8B6CFF),
                contentColor = Color.White,
                onClick = {},
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
