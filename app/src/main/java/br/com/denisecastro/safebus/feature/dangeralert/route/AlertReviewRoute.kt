package br.com.denisecastro.safebus.feature.dangeralert.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.denisecastro.safebus.feature.dangeralert.screens.AlertReviewScreen
import br.com.denisecastro.safebus.feature.dangeralert.viewmodel.EmergencyAlertViewModel

@Composable
fun AlertReviewRoute(
    viewModel: EmergencyAlertViewModel,
    onConfirmClick: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val location by viewModel.location.collectAsStateWithLifecycle()

    location?.let {
        AlertReviewScreen(
            location = it,
            onConfirmClick = onConfirmClick,
            onBackClick = onBackClick,
            modifier = modifier
        )
    }
}
