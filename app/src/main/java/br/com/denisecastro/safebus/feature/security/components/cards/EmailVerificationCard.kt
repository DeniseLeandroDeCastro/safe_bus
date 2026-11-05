package br.com.denisecastro.safebus.feature.security.components.cards

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
import br.com.denisecastro.safebus.feature.profile.components.cards.SectionCard
import br.com.denisecastro.safebus.ui.components.buttons.secondary.SecondaryButton
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme

@Composable
fun EmailVerificationCard(
    isEmailVerified: Boolean,
    onVerifyEmailClick: () -> Unit,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false
) {
    SectionCard(
        titleRes = R.string.security_email_verification_title,
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = stringResource(
                    if (isEmailVerified) {
                        R.string.security_email_verified
                    } else {
                        R.string.security_email_not_verified
                    }
                ),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            if (!isEmailVerified) {
                SecondaryButton(
                    text = stringResource(
                        R.string.security_verify_email_button
                    ),
                    onClick = onVerifyEmailClick,
                    modifier = Modifier.fillMaxWidth(),
                    isLoading = isLoading
                )
            }
        }
    }
}

@Preview(name = "Verificação - Claro", showBackground = true)
@Composable
private fun EmailVerificationCardLightPreview() {
    SafeBusTheme(darkTheme = false) {
        Surface {
            EmailVerificationCard(
                isEmailVerified = false,
                onVerifyEmailClick = {}
            )
        }
    }
}

@Preview(name = "Verificação - Escuro", showBackground = true)
@Composable
private fun EmailVerificationCardDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        Surface {
            EmailVerificationCard(
                isEmailVerified = true,
                onVerifyEmailClick = {}
            )
        }
    }
}


