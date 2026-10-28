package br.com.denisecastro.safebus.feature.login.components.buttons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme

@Composable
fun GoogleSignInButton(
    onClick: () -> Unit,
    enabled: Boolean,
    isLoading: Boolean,
    modifier: Modifier = Modifier
) {
    OutlinedButton(
        onClick = onClick,
        enabled = enabled && !isLoading,
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    strokeWidth = 2.dp
                )
            }

            Text("Continuar com Google")
        }
    }
}

@Preview(name = "Google - Claro")
@Composable
private fun GoogleSignInButtonLightPreview() {
    SafeBusTheme(darkTheme = false) {
        GoogleSignInButton(
            onClick = {},
            enabled = true,
            isLoading = false,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(name = "Google - Escuro")
@Composable
private fun GoogleSignInButtonDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        GoogleSignInButton(
            onClick = {},
            enabled = true,
            isLoading = false,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
