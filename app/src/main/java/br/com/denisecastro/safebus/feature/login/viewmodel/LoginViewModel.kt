package br.com.denisecastro.safebus.feature.login.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.denisecastro.safebus.feature.login.model.LoginUiState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import com.google.firebase.auth.GoogleAuthProvider


class LoginViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onEmailChange(email: String) {
        _uiState.update {
            it.copy(email = email, errorMessage = null)
        }
    }

    fun onPasswordChange(password: String) {
        _uiState.update {
            it.copy(password = password, errorMessage = null)
        }
    }

    fun login(onSuccess: () -> Unit) {
        val state = _uiState.value

        if (!state.isFormValid || state.isLoading) return

        _uiState.update {
            it.copy(isLoading = true, errorMessage = null)
        }

        viewModelScope.launch {
            try {
                auth.signInWithEmailAndPassword(
                    state.email.trim(),
                    state.password
                ).await()

                _uiState.update {
                    it.copy(isLoading = false)
                }

                onSuccess()
            } catch (exception: Exception) {
                val message = when (
                    (exception as? FirebaseAuthException)?.errorCode
                ) {
                    "ERROR_INVALID_EMAIL" ->
                        "O e-mail informado é inválido."

                    "ERROR_INVALID_CREDENTIAL",
                    "ERROR_WRONG_PASSWORD",
                    "ERROR_USER_NOT_FOUND" ->
                        "E-mail ou senha incorretos."

                    "ERROR_USER_DISABLED" ->
                        "Esta conta foi desativada."

                    "ERROR_TOO_MANY_REQUESTS" ->
                        "Muitas tentativas. Tente novamente mais tarde."

                    else ->
                        "Não foi possível entrar. Verifique sua conexão e tente novamente."
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

    fun sendPasswordResetEmail(
        email: String,
        onResult: (Boolean, String) -> Unit
    ) {
        if (email.isBlank() || !android.util.Patterns.EMAIL_ADDRESS
                .matcher(email.trim()).matches()
        ) {
            onResult(false, "Informe um e-mail válido.")
            return
        }

        viewModelScope.launch {
            try {
                auth.sendPasswordResetEmail(email.trim()).await()

                onResult(
                    true,
                    "Se o e-mail estiver cadastrado, você receberá " +
                            "um link para redefinir sua senha."
                )
            } catch (exception: Exception) {
                val message = when (
                    (exception as? FirebaseAuthException)?.errorCode
                ) {
                    "ERROR_INVALID_EMAIL" ->
                        "Informe um e-mail válido."

                    "ERROR_TOO_MANY_REQUESTS" ->
                        "Muitas solicitações. Tente novamente mais tarde."

                    else ->
                        "Não foi possível solicitar a recuperação. " +
                                "Verifique sua conexão e tente novamente."
                }

                onResult(false, message)
            }
        }
    }

    fun loginWithGoogle(
        idToken: String,
        onSuccess: () -> Unit
    ) {
        if (_uiState.value.isLoading) return

        _uiState.update {
            it.copy(
                isLoading = true,
                errorMessage = null
            )
        }

        viewModelScope.launch {
            try {
                val credential = GoogleAuthProvider.getCredential(
                    idToken,
                    null
                )

                auth.signInWithCredential(credential).await()

                _uiState.update {
                    it.copy(isLoading = false)
                }

                onSuccess()
            } catch (exception: Exception) {
                Log.e(
                    "SafeBusGoogleLogin",
                    "Erro ao autenticar com Firebase",
                    exception
                )

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = "Não foi possível entrar com Google. Tente novamente."
                    )
                }
            }
        }
    }

    fun onGoogleLoginError() {
        _uiState.update {
            it.copy(
                errorMessage = "Não foi possível entrar com Google. Tente novamente."
            )
        }
    }
}
