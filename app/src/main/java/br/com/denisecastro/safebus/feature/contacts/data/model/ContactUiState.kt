package br.com.denisecastro.safebus.feature.contacts.data.model

data class ContactUiState(
    val name: String = "",
    val phone: String = "",
    val relationship: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null
) {
    val isFormValid: Boolean
        get() = name.isNotBlank() && phone.isNotBlank()
}
