package br.com.denisecastro.safebus.feature.profile.components.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import br.com.denisecastro.safebus.R
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme

@Composable
fun LogoutConfirmationDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = stringResource(R.string.profile_logout)
            )
        },
        text = {
            Text(
                text = stringResource(
                    R.string.profile_logout_confirmation
                )
            )
        },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(
                    text = stringResource(R.string.action_exit),
                    color = MaterialTheme.colorScheme.error
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(
                    text = stringResource(R.string.action_cancel),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    )
}

@Preview(
    name = "Logout - Tema claro",
    showSystemUi = true
)
@Composable
private fun LogoutConfirmationDialogLightPreview() {
    SafeBusTheme(darkTheme = false) {
        LogoutConfirmationDialog(
            onConfirm = {},
            onDismiss = {}
        )
    }
}

@Preview(
    name = "Logout - Tema escuro",
    showSystemUi = true
)
@Composable
private fun LogoutConfirmationDialogDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        LogoutConfirmationDialog(
            onConfirm = {},
            onDismiss = {}
        )
    }
}
