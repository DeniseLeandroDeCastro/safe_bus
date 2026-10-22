package br.com.denisecastro.safebus.feature.register.viewmodel

import android.util.Patterns
import androidx.lifecycle.ViewModel
import br.com.denisecastro.safebus.feature.register.model.RegisterUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RegisterViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())

    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    fun onNameChange(value: String) {
        updateForm {
            copy(name = value)
        }
    }

    fun onEmailChange(value: String) {
        updateForm {
            copy(email = value)
        }
    }

    fun onPasswordChange(value: String) {
        updateForm {
            copy(password = value)
        }
    }

    fun onConfirmPasswordChange(value: String) {
        updateForm {
            copy(confirmPassword = value)
        }
    }

    private fun updateForm(
        update: RegisterUiState.() -> RegisterUiState
    ) {
        _uiState.update { currentState ->
            if (currentState.isLoading) {
                return@update currentState
            }

            val updatedState = currentState.update()

            updatedState.copy(
                isFormValid = validateForm(updatedState)
            )
        }
    }

    private fun validateForm(
        state: RegisterUiState
    ): Boolean {
        val isNameValid = state.name.isNotBlank()

        val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(
            state.email.trim()
        ).matches()

        val isPasswordValid = state.password.length >= 8

        val isConfirmPasswordValid =
            state.confirmPassword.isNotEmpty() &&
                    state.password == state.confirmPassword

        return isNameValid &&
                isEmailValid &&
                isPasswordValid &&
                isConfirmPasswordValid
    }
}
