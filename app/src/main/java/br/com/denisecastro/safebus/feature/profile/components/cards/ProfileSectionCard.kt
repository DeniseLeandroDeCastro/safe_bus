package br.com.denisecastro.safebus.feature.profile.components.cards

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.denisecastro.safebus.R
import br.com.denisecastro.safebus.feature.profile.components.optionrow.ProfileOptionRow
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme

@Composable
fun ProfileSectionCard(
    @StringRes titleRes: Int,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = stringResource(titleRes),
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(12.dp))

            content()
        }
    }
}

@Preview(
    name = "ProfileSectionCard - Tema claro",
    showSystemUi = true
)
@Composable
private fun ProfileSectionCardLightPreview() {
    SafeBusTheme(darkTheme = false) {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            ProfileSectionCard(
                titleRes = R.string.profile_account_section,
                modifier = Modifier.padding(16.dp)
            ) {
                ProfileOptionRow(
                    titleRes = R.string.profile_personal_data,
                    descriptionRes = R.string.profile_personal_data_description,
                    icon = Icons.Outlined.PersonOutline
                )

                HorizontalDivider()

                ProfileOptionRow(
                    titleRes = R.string.profile_security,
                    descriptionRes = R.string.profile_security_description,
                    icon = Icons.Outlined.Lock
                )
            }
        }
    }
}

@Preview(
    name = "ProfileSectionCard - Tema escuro",
    showSystemUi = true
)
@Composable
private fun ProfileSectionCardDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            ProfileSectionCard(
                titleRes = R.string.profile_account_section,
                modifier = Modifier.padding(16.dp)
            ) {
                ProfileOptionRow(
                    titleRes = R.string.profile_personal_data,
                    descriptionRes = R.string.profile_personal_data_description,
                    icon = Icons.Outlined.PersonOutline
                )

                HorizontalDivider()

                ProfileOptionRow(
                    titleRes = R.string.profile_security,
                    descriptionRes = R.string.profile_security_description,
                    icon = Icons.Outlined.Lock
                )
            }
        }
    }
}
