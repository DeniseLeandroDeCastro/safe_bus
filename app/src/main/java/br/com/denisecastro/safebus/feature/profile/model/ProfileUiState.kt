package br.com.denisecastro.safebus.feature.profile.model

data class ProfileUiState(
    val name: String = "",
    val email: String = "",
    val photoUrl: String? = null,
    val isEmailVerified: Boolean = false
)
