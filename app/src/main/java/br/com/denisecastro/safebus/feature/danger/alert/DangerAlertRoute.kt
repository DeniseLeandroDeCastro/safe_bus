package br.com.denisecastro.safebus.feature.danger.alert

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.denisecastro.safebus.feature.danger.screens.DangerAlertScreen

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