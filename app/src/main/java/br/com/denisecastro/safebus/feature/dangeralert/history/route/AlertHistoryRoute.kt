package br.com.denisecastro.safebus.feature.dangeralert.history.route

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.denisecastro.safebus.feature.dangeralert.history.viewmodel.AlertHistoryViewModel
import br.com.denisecastro.safebus.feature.dangeralert.screens.AlertHistoryScreen

@Composable
fun AlertHistoryRoute(
    onBackClick: () -> Unit,
    viewModel: AlertHistoryViewModel = viewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    AlertHistoryScreen(
        uiState = uiState.value,
        onBackClick = onBackClick,
        onRetryClick = viewModel::loadAlertHistory
    )
}
