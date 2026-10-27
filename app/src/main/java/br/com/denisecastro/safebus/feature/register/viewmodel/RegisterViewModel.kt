package br.com.denisecastro.safebus.feature.register.viewmodel

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.denisecastro.safebus.feature.register.model.RegisterUiState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class RegisterViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()

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

    fun register(onSuccess: () -> Unit) {
        val state = _uiState.value

        if (!state.isFormValid || state.isLoading) return

        _uiState.update {
            it.copy(
                isLoading = true,
                errorMessage = null
            )
        }

        viewModelScope.launch {
            try {
                val result = auth.createUserWithEmailAndPassword(
                    state.email.trim(),
                    state.password
                ).await()

                val profileUpdates = UserProfileChangeRequest.Builder()
                    .setDisplayName(state.name.trim())
                    .build()

                result.user?.updateProfile(profileUpdates)?.await()

                _uiState.update {
                    it.copy(isLoading = false)
                }

                onSuccess()

            } catch (exception: Exception) {
                val message = when (
                    (exception as? FirebaseAuthException)?.errorCode
                ) {
                    "ERROR_EMAIL_ALREADY_IN_USE" ->
                        "Este e-mail já está cadastrado."

                    "ERROR_INVALID_EMAIL" ->
                        "O e-mail informado é inválido."

                    "ERROR_WEAK_PASSWORD" ->
                        "A senha informada não atende aos requisitos de segurança."

                    else ->
                        "Não foi possível concluir o cadastro. Tente novamente."
                }

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = message
                    )
                }
            }
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
                isFormValid = validateForm(updatedState),
                errorMessage = null
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
