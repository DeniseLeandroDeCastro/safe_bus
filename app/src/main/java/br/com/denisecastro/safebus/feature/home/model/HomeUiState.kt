package br.com.denisecastro.safebus.feature.home.model

data class HomeUiState(
    val userName: String = "",
    val transportLine: String = "",
    val transportOrigin: String = "",
    val transportDestination: String = "",
    val isLoading: Boolean = false
)
