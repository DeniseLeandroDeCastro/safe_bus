package br.com.denisecastro.safebus.feature.dangeralert.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.denisecastro.safebus.R
import br.com.denisecastro.safebus.feature.dangeralert.components.cards.AlertInformationCard
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme

@Composable
fun DangerAlertScreen(
    onBackClick: () -> Unit,
    onConfirmClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.navigationBars)
                .verticalScroll(rememberScrollState())
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                    top = 16.dp,
                    bottom = 24.dp
                ),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(
                            R.string.danger_alert_back
                        )
                    )
                }

                Text(
                    text = stringResource(
                        R.string.danger_alert_screen_title
                    ),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            Image(
                painter = painterResource(R.drawable.alert_danger),
                contentDescription = stringResource(
                    R.string.danger_alert_image_description
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2.4f)
                    .clip(RoundedCornerShape(20.dp)),
                contentScale = ContentScale.Crop
            )

            Text(
                text = stringResource(R.string.danger_alert_title),
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                text = stringResource(R.string.danger_alert_description),
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.padding(bottom = 4.dp))

            AlertInformationCard()

            Spacer(modifier = Modifier.weight(1f, fill = false))

            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = onConfirmClick,
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.extraLarge
                ) {
                    Text(
                        text = stringResource(
                            R.string.danger_alert_confirm
                        ),
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }

                OutlinedButton(
                    onClick = onBackClick,
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.extraLarge
                ) {
                    Text(
                        text = stringResource(
                            R.string.danger_alert_cancel
                        ),
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }
        }
    }
}

@Preview(name = "Alerta de perigo - Claro", showSystemUi = true)
@Composable
private fun DangerAlertScreenLightPreview() {
    SafeBusTheme(darkTheme = false) {
        DangerAlertScreen(
            onBackClick = {},
            onConfirmClick = {}
        )
    }
}

@Preview(name = "Alerta de perigo - Escuro", showSystemUi = true)
@Composable
private fun DangerAlertScreenDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        DangerAlertScreen(
            onBackClick = {},
            onConfirmClick = {}
        )
    }
}
