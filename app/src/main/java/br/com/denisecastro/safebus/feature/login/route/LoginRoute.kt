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
import android.app.Activity
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.CustomCredential
import androidx.credentials.exceptions.GetCredentialCancellationException
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import br.com.denisecastro.safebus.R
import kotlinx.coroutines.launch
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource


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

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var isGoogleLoading by remember {
        mutableStateOf(false)
    }

    val credentialManager = remember(context) {
        CredentialManager.create(context)
    }
    val webClientId = stringResource(R.string.default_web_client_id)

    val onGoogleLoginClick: () -> Unit = {
        if (!isGoogleLoading && !uiState.isLoading) {
            isGoogleLoading = true

            scope.launch {
                try {
                    val googleIdOption = GetGoogleIdOption.Builder()
                        .setFilterByAuthorizedAccounts(false)
                        .setServerClientId(webClientId)
                        .build()

                    val request = GetCredentialRequest.Builder()
                        .addCredentialOption(googleIdOption)
                        .build()

                    val result = credentialManager.getCredential(
                        context = context as Activity,
                        request = request
                    )

                    val credential = result.credential

                    if (
                        credential is CustomCredential &&
                        credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
                    ) {
                        val googleCredential =
                            GoogleIdTokenCredential.createFrom(credential.data)

                        viewModel.loginWithGoogle(
                            idToken = googleCredential.idToken,
                            onSuccess = onLoginClick
                        )
                    } else {
                        viewModel.onGoogleLoginError()
                    }
                } catch (_: GetCredentialCancellationException) {
                    // O usuário cancelou a seleção da conta.
                } catch (exception: Exception) {
                    Log.e(
                        "SafeBusGoogleLogin",
                        "Erro ao iniciar login com Google",
                        exception
                    )
                    viewModel.onGoogleLoginError()
                } finally {
                    isGoogleLoading = false
                }
            }
        }
    }

    LoginScreen(
        uiState = uiState,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onBackClick = onBackClick,
        onLoginClick = {
            viewModel.login(onSuccess = onLoginClick)
        },
        onGoogleLoginClick = onGoogleLoginClick,
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
