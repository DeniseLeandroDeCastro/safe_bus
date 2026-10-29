package br.com.denisecastro.safebus.feature.dangeralert.data.model

sealed interface EmergencyAlertSendState {

    data object Idle : EmergencyAlertSendState

    data object Loading : EmergencyAlertSendState

    data class Success(
        val alertId: String
    ) : EmergencyAlertSendState

    data object Error : EmergencyAlertSendState
}
