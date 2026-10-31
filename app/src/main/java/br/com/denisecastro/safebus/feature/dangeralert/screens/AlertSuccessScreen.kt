package br.com.denisecastro.safebus.feature.dangeralert.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.denisecastro.safebus.R
import br.com.denisecastro.safebus.feature.dangeralert.components.cards.AlertSuccessCard
import br.com.denisecastro.safebus.feature.dangeralert.components.cards.AlertSuccessInfoCard
import br.com.denisecastro.safebus.ui.components.buttons.primary.PrimaryButton
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme

@Composable
fun AlertSuccessScreen(
    alertId: String,
    onHomeClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .windowInsetsPadding(WindowInsets.navigationBars)
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Spacer(modifier = Modifier.weight(0.4f))

        AlertSuccessCard(
            alertId = alertId
        )

        AlertSuccessInfoCard()

        Spacer(modifier = Modifier.weight(1f))

        PrimaryButton(
            text = stringResource(R.string.alert_success_home),
            onClick = onHomeClick,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(name = "Confirmação - Claro", showSystemUi = true)
@Composable
private fun AlertSuccessScreenLightPreview() {
    SafeBusTheme(darkTheme = false) {
        AlertSuccessScreen(
            alertId = "EXEMPLO-123",
            onHomeClick = {}
        )
    }
}

@Preview(name = "Confirmação - Escuro", showSystemUi = true)
@Composable
private fun AlertSuccessScreenDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        AlertSuccessScreen(
            alertId = "EXEMPLO-123",
            onHomeClick = {}
        )
    }
}
