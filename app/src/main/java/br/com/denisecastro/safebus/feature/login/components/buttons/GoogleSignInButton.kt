package br.com.denisecastro.safebus.feature.login.components.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.denisecastro.safebus.R
import br.com.denisecastro.safebus.ui.components.buttons.secondary.SecondaryButton
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme

@Composable
fun GoogleSignInButton(
    onClick: () -> Unit,
    enabled: Boolean,
    isLoading: Boolean,
    modifier: Modifier = Modifier
) {
    SecondaryButton(
        text = stringResource(R.string.google_sign_in_button),
        onClick = onClick,
        enabled = enabled,
        isLoading = isLoading,
        modifier = modifier,
        leadingIcon = {
            Image(
                painter = painterResource(R.drawable.ic_google),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
        }
    )
}

@Preview(name = "Google - Tema claro", showSystemUi = true)
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

@Preview(name = "Google - Tema escuro", showSystemUi = true)
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

@Preview(name = "Google - Carregando", showSystemUi = true)
@Composable
private fun GoogleSignInButtonLoadingPreview() {
    SafeBusTheme(darkTheme = false) {
        GoogleSignInButton(
            onClick = {},
            enabled = false,
            isLoading = true,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
