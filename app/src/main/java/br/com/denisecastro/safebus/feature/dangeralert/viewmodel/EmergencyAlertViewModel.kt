package br.com.denisecastro.safebus.feature.dangeralert.viewmodel

import androidx.lifecycle.ViewModel
import br.com.denisecastro.safebus.feature.dangeralert.model.AlertLocationUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class EmergencyAlertViewModel : ViewModel() {

    private val _location =
        MutableStateFlow<AlertLocationUiState.Success?>(null)

    val location = _location.asStateFlow()

    fun setLocation(value: AlertLocationUiState.Success) {
        _location.value = value
    }

    fun clearLocation() {
        _location.value = null
    }
}
