package br.com.denisecastro.safebus.feature.dangeralert.location.viewmodel

import android.Manifest
import android.annotation.SuppressLint
import android.app.Application
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.com.denisecastro.safebus.feature.dangeralert.location.model.AlertLocationUiState
import com.google.android.gms.location.CurrentLocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withTimeoutOrNull
import kotlin.time.Duration.Companion.seconds

class AlertLocationViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val locationClient =
        LocationServices.getFusedLocationProviderClient(application)

    private val _uiState = MutableStateFlow<AlertLocationUiState>(
        AlertLocationUiState.Loading
    )

    val uiState = _uiState.asStateFlow()

    fun checkLocationPermission() {
        val context = getApplication<Application>()

        val fineGranted = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val coarseGranted = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        if (fineGranted || coarseGranted) {
            fetchLocation()
        } else {
            _uiState.value = AlertLocationUiState.PermissionRequired
        }
    }

    fun onPermissionDenied() {
        _uiState.value = AlertLocationUiState.PermissionDenied
    }

    @SuppressLint("MissingPermission")
    fun fetchLocation() {
        val context = getApplication<Application>()

        val fineGranted = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val coarseGranted = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        if (!fineGranted && !coarseGranted) {
            _uiState.value = AlertLocationUiState.PermissionRequired
            return
        }

        _uiState.value = AlertLocationUiState.Loading

        viewModelScope.launch {
            val cancellationTokenSource = CancellationTokenSource()

            try {
                val request = CurrentLocationRequest.Builder()
                    .setPriority(
                        if (fineGranted) {
                            Priority.PRIORITY_HIGH_ACCURACY
                        } else {
                            Priority.PRIORITY_BALANCED_POWER_ACCURACY
                        }
                    )
                    .setMaxUpdateAgeMillis(0L)
                    .build()

                val location = withTimeoutOrNull(15.seconds) {
                    locationClient.getCurrentLocation(
                        request,
                        cancellationTokenSource.token
                    ).await()
                }

                _uiState.value = if (location != null) {
                    AlertLocationUiState.Success(
                        latitude = location.latitude,
                        longitude = location.longitude,
                        accuracyMeters = if (location.hasAccuracy()) {
                            location.accuracy
                        } else {
                            null
                        }
                    )
                } else {
                    AlertLocationUiState.LocationUnavailable
                }
            } catch (exception: Exception) {
                _uiState.value = AlertLocationUiState.LocationUnavailable
            } finally {
                cancellationTokenSource.cancel()
            }
        }
    }
}
