package br.com.denisecastro.safebus.feature.profile.model

data class ProfileUiState(
    val name: String = "",
    val email: String = "",
    val photoUrl: String? = null,
    val isEmailVerified: Boolean = false,
    val isSaving: Boolean = false,
    val nameUpdateSuccess: Boolean = false,
    val nameUpdateError: Boolean = false
)
