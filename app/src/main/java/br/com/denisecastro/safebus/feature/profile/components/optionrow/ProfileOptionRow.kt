package br.com.denisecastro.safebus.feature.profile.components.optionrow

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import br.com.denisecastro.safebus.R
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PersonOutline

@Composable
fun ProfileOptionRow(
    @StringRes titleRes: Int,
    @StringRes descriptionRes: Int?,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .then(
                if (onClick != null) {
                    Modifier.clickable(onClick = onClick)
                } else {
                    Modifier
                }
            )
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Surface(
            shape = CircleShape,
            color = MaterialTheme.colorScheme.primaryContainer
        ) {
            Box(
                modifier = Modifier.size(52.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = stringResource(titleRes),
                style = MaterialTheme.typography.titleMedium
            )

            if (descriptionRes != null) {
                Text(
                    text = stringResource(descriptionRes),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        Icon(
            imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Preview(
    name = "ProfileOptionRow - Tema claro",
    showSystemUi = true
)
@Composable
private fun ProfileOptionRowLightPreview() {
    SafeBusTheme(darkTheme = false) {
        Surface {
            ProfileOptionRow(
                titleRes = R.string.profile_personal_data,
                descriptionRes = R.string.profile_personal_data_description,
                icon = Icons.Outlined.PersonOutline
            )
        }
    }
}

@Preview(
    name = "ProfileOptionRow - Tema escuro",
    showSystemUi = true
)
@Composable
private fun ProfileOptionRowDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        Surface {
            ProfileOptionRow(
                titleRes = R.string.profile_personal_data,
                descriptionRes = R.string.profile_personal_data_description,
                icon = Icons.Outlined.PersonOutline
            )
        }
    }
}
