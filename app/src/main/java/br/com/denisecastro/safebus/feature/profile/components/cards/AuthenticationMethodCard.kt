
package br.com.denisecastro.safebus.feature.profile.components.security

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.VerifiedUser
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.denisecastro.safebus.R
import br.com.denisecastro.safebus.feature.profile.components.cards.SectionCard
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme

@Composable
fun AuthenticationMethodCard(
    authenticationMethod: String,
    modifier: Modifier = Modifier
) {
    SectionCard(
        titleRes = R.string.security_authentication_method,
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.VerifiedUser,
                contentDescription = null,
                modifier = Modifier.size(32.dp),
                tint = MaterialTheme.colorScheme.primary
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = authenticationMethod,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = stringResource(R.string.security_authentication_description),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Preview(name = "Autenticação - Claro", showBackground = true)
@Composable
private fun AuthenticationMethodCardLightPreview() {
    SafeBusTheme(darkTheme = false) {
        Surface {
            AuthenticationMethodCard(
                authenticationMethod = stringResource(
                    R.string.security_email_password
                )
            )
        }
    }
}

@Preview(name = "Autenticação - Escuro", showBackground = true)
@Composable
private fun AuthenticationMethodCardDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        Surface {
            AuthenticationMethodCard(
                authenticationMethod = stringResource(
                    R.string.security_email_password
                )
            )
        }
    }
}
