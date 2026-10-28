package br.com.denisecastro.safebus.feature.login.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.denisecastro.safebus.feature.login.components.dialog.ForgotPasswordDialog
import br.com.denisecastro.safebus.feature.login.screen.LoginScreen
import br.com.denisecastro.safebus.feature.login.viewmodel.LoginViewModel

@Composable
fun LoginRoute(
    onBackClick: () -> Unit,
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
    viewModel: LoginViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var showForgotPasswordDialog by remember {
        mutableStateOf(false)
    }

    var isResetLoading by remember {
        mutableStateOf(false)
    }

    var resetMessage by remember {
        mutableStateOf<String?>(null)
    }

    var resetSuccess by remember {
        mutableStateOf(false)
    }

    LoginScreen(
        uiState = uiState,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onBackClick = onBackClick,
        onLoginClick = {
            viewModel.login(onSuccess = onLoginClick)
        },
        onForgotPasswordClick = {
            resetMessage = null
            resetSuccess = false
            showForgotPasswordDialog = true
        },
        onRegisterClick = onRegisterClick
    )

    if (showForgotPasswordDialog) {
        ForgotPasswordDialog(
            initialEmail = uiState.email,
            isLoading = isResetLoading,
            message = resetMessage,
            isSuccess = resetSuccess,
            onDismiss = {
                if (!isResetLoading) {
                    showForgotPasswordDialog = false
                    resetMessage = null
                    resetSuccess = false
                }
            },
            onSendClick = { email ->
                if (!isResetLoading) {
                    isResetLoading = true
                    resetMessage = null

                    viewModel.sendPasswordResetEmail(email) {
                            success, message ->

                        isResetLoading = false
                        resetSuccess = success
                        resetMessage = message
                    }
                }
            }
        )
    }
}
