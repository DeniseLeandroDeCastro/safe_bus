package br.com.denisecastro.safebus.feature.dangeralert.model

sealed interface AlertLocationUiState {
    data object Loading : AlertLocationUiState
    data object PermissionRequired : AlertLocationUiState
    data class Success(
        val latitude: Double,
        val longitude: Double,
        val accuracyMeters: Float?
    ) : AlertLocationUiState
    data object LocationUnavailable : AlertLocationUiState
    data object PermissionDenied : AlertLocationUiState
}
