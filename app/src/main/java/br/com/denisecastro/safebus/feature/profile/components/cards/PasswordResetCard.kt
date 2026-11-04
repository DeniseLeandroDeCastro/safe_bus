package br.com.denisecastro.safebus.feature.profile.components.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.denisecastro.safebus.R
import br.com.denisecastro.safebus.ui.components.buttons.primary.PrimaryButton
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme

@Composable
fun PasswordResetCard(
    onResetPasswordClick: () -> Unit,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false
) {
    SectionCard(
        titleRes = R.string.security_reset_password_title,
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = stringResource(
                    R.string.security_reset_password_description
                ),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            PrimaryButton(
                text = stringResource(
                    R.string.security_reset_password_button
                ),
                onClick = onResetPasswordClick,
                modifier = Modifier.fillMaxWidth(),
                isLoading = isLoading
            )
        }
    }
}

@Preview(name = "Redefinir senha - Claro", showBackground = true)
@Composable
private fun PasswordResetCardLightPreview() {
    SafeBusTheme(darkTheme = false) {
        Surface {
            PasswordResetCard(
                onResetPasswordClick = {}
            )
        }
    }
}

@Preview(name = "Redefinir senha - Escuro", showBackground = true)
@Composable
private fun PasswordResetCardDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        Surface {
            PasswordResetCard(
                onResetPasswordClick = {}
            )
        }
    }
}
