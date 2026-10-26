package br.com.denisecastro.safebus.feature.home.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.denisecastro.safebus.feature.register.screen.RegisterScreen
import br.com.denisecastro.safebus.feature.register.viewmodel.RegisterViewModel

@Composable
fun RegisterRoute(
    onBackClick: () -> Unit,
    onRegisterClick: () -> Unit,
    onLoginClick: () -> Unit,
    viewModel: RegisterViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    RegisterScreen(
        uiState = uiState,
        onNameChange = viewModel::onNameChange,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onConfirmPasswordChange = viewModel::onConfirmPasswordChange,
        onBackClick = onBackClick,
        onRegisterClick = onRegisterClick,
        onLoginClick = onLoginClick
    )
}
