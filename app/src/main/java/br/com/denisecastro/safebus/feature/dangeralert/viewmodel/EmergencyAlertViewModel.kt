package br.com.denisecastro.safebus.feature.dangeralert.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.denisecastro.safebus.feature.dangeralert.data.model.EmergencyAlert
import br.com.denisecastro.safebus.feature.dangeralert.data.model.EmergencyAlertSendState
import br.com.denisecastro.safebus.feature.dangeralert.data.repository.EmergencyAlertRepository
import br.com.denisecastro.safebus.feature.dangeralert.model.AlertLocationUiState
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EmergencyAlertViewModel : ViewModel() {

    private val repository = EmergencyAlertRepository()

    private val _location =
        MutableStateFlow<AlertLocationUiState.Success?>(null)

    val location = _location.asStateFlow()

    private val _sendState =
        MutableStateFlow<EmergencyAlertSendState>(
            EmergencyAlertSendState.Idle
        )

    val sendState = _sendState.asStateFlow()

    fun setLocation(value: AlertLocationUiState.Success) {
        _location.value = value
        _sendState.value = EmergencyAlertSendState.Idle
    }

    fun registerAlert() {
        if (_sendState.value is EmergencyAlertSendState.Loading ||
            _sendState.value is EmergencyAlertSendState.Success
        ) {
            return
        }

        val userId = FirebaseAuth.getInstance().currentUser?.uid
        val currentLocation = _location.value

        if (userId == null || currentLocation == null) {
            _sendState.value = EmergencyAlertSendState.Error
            return
        }

        _sendState.value = EmergencyAlertSendState.Loading

        viewModelScope.launch {
            val alert = EmergencyAlert(
                userId = userId,
                type = "DANGER",
                latitude = currentLocation.latitude,
                longitude = currentLocation.longitude,
                accuracyMeters = currentLocation.accuracyMeters?.toDouble()
            )

            val result = repository.registerAlert(alert)

            _sendState.value = result.fold(
                onSuccess = { alertId ->
                    EmergencyAlertSendState.Success(alertId)
                },
                onFailure = {
                    EmergencyAlertSendState.Error
                }
            )
        }
    }

    fun clearLocation() {
        _location.value = null
        _sendState.value = EmergencyAlertSendState.Idle
    }
}
