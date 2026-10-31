package br.com.denisecastro.safebus.feature.dangeralert.history.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.denisecastro.safebus.feature.dangeralert.data.repository.EmergencyAlertRepository
import br.com.denisecastro.safebus.feature.dangeralert.history.state.AlertHistoryUiState
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AlertHistoryViewModel(
    private val repository: EmergencyAlertRepository = EmergencyAlertRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(AlertHistoryUiState())
    val uiState: StateFlow<AlertHistoryUiState> = _uiState.asStateFlow()

    init {
        loadAlertHistory()
    }

    fun loadAlertHistory() {
        val userId = FirebaseAuth.getInstance().currentUser?.uid

        if (userId == null) {
            _uiState.value = AlertHistoryUiState(
                hasError = true
            )
            return
        }

        viewModelScope.launch {
            _uiState.value = AlertHistoryUiState(
                isLoading = true
            )

            repository.getAlertHistory(userId)
                .onSuccess { alerts ->
                    _uiState.value = AlertHistoryUiState(
                        alerts = alerts
                    )
                }
                .onFailure { exception ->
                    android.util.Log.e(
                        "AlertHistory",
                        "Erro ao consultar histórico de alertas",
                        exception
                    )

                    _uiState.value = AlertHistoryUiState(
                        hasError = true
                    )
                }
        }
    }
}
