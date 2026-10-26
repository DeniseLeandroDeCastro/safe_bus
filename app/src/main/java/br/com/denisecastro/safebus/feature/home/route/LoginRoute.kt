package br.com.denisecastro.safebus.feature.home.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.denisecastro.safebus.feature.login.screen.LoginScreen
import br.com.denisecastro.safebus.feature.login.viewmodel.LoginViewModel

@Composable
fun LoginRoute(
    onBackClick: () -> Unit,
    onLoginClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onRegisterClick: () -> Unit,
    viewModel: LoginViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LoginScreen(
        uiState = uiState,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onBackClick = onBackClick,
        onLoginClick = {
            viewModel.login(onSuccess = onLoginClick)
        },
        onForgotPasswordClick = onForgotPasswordClick,
        onRegisterClick = onRegisterClick
    )
}
