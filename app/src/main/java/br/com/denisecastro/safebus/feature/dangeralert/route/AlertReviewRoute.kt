package br.com.denisecastro.safebus.feature.dangeralert.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.denisecastro.safebus.feature.dangeralert.data.model.EmergencyAlertSendState
import br.com.denisecastro.safebus.feature.dangeralert.screens.AlertReviewScreen
import br.com.denisecastro.safebus.feature.dangeralert.viewmodel.EmergencyAlertViewModel

@Composable
fun AlertReviewRoute(
    viewModel: EmergencyAlertViewModel,
    onSuccess: (String) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val location by viewModel.location.collectAsStateWithLifecycle()
    val sendState by viewModel.sendState.collectAsStateWithLifecycle()
    val alertType by viewModel.alertType.collectAsStateWithLifecycle()

    LaunchedEffect(sendState) {
        val success = sendState as? EmergencyAlertSendState.Success

        if (success != null) {
            onSuccess(success.alertId)
        }
    }

    location?.let { currentLocation ->
        AlertReviewScreen(
            location = currentLocation,
            alertType = alertType,
            sendState = sendState,
            onConfirmClick = viewModel::registerAlert,
            onBackClick = onBackClick,
            modifier = modifier
        )
    }
}
