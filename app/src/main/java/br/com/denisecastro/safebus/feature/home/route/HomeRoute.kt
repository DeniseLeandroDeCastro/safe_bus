package br.com.denisecastro.safebus.feature.home.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.denisecastro.safebus.feature.home.screens.HomeScreen
import br.com.denisecastro.safebus.feature.home.viewmodel.HomeViewModel

@Composable
fun HomeRoute(
    onNotificationsClick: () -> Unit,
    onProfileClick: () -> Unit,
    onLogoutClick: () -> Unit,
    onTransportClick: () -> Unit,
    onDangerAlertClick: () -> Unit,
    onHarassmentAlertClick: () -> Unit,
    onSafetyTipsClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(
        uiState = uiState,
        onNotificationsClick = onNotificationsClick,
        onProfileClick = onProfileClick,
        onLogoutClick = onLogoutClick,
        onTransportClick = onTransportClick,
        onDangerAlertClick = onDangerAlertClick,
        onHarassmentAlertClick = onHarassmentAlertClick,
        onSafetyTipsClick = onSafetyTipsClick,
        modifier = modifier
    )
}
