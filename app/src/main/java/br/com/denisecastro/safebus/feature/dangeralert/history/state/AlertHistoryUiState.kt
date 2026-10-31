package br.com.denisecastro.safebus.feature.dangeralert.history.state

import br.com.denisecastro.safebus.feature.dangeralert.data.model.AlertHistoryItem

data class AlertHistoryUiState(
    val isLoading: Boolean = false,
    val alerts: List<AlertHistoryItem> = emptyList(),
    val hasError: Boolean = false
)
