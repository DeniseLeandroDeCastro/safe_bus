package br.com.denisecastro.safebus.feature.dangeralert.route

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.denisecastro.safebus.feature.dangeralert.model.AlertLocationUiState
import br.com.denisecastro.safebus.feature.dangeralert.screens.AlertLocationScreen
import br.com.denisecastro.safebus.feature.dangeralert.viewmodel.AlertLocationViewModel

@Composable
fun AlertLocationRoute(
    onBackClick: () -> Unit,
    onContinueClick: (AlertLocationUiState.Success) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AlertLocationViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val granted = permissions.values.any { it }

        if (granted) {
            viewModel.fetchLocation()
        } else {
            viewModel.onPermissionDenied()
        }
    }

    LaunchedEffect(Unit) {
        viewModel.checkLocationPermission()
    }

    AlertLocationScreen(
        uiState = uiState,
        onPermissionClick = {
            permissionLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        },
        onRetryClick = viewModel::checkLocationPermission,
        onContinueClick = {
            val location = uiState as? AlertLocationUiState.Success
            if (location != null) {
                onContinueClick(location)
            }
        },
        onBackClick = onBackClick,
        modifier = modifier
    )
}
