package br.com.denisecastro.safebus.feature.dangeralert.route

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.denisecastro.safebus.feature.dangeralert.screens.DangerAlertScreen

@Composable
fun DangerAlertRoute(
    onBackClick: () -> Unit,
    onConfirmClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    DangerAlertScreen(
        onBackClick = onBackClick,
        onConfirmClick = onConfirmClick,
        modifier = modifier
    )
}