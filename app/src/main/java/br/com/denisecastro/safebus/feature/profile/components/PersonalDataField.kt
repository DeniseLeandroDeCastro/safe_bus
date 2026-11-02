package br.com.denisecastro.safebus.feature.profile.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.denisecastro.safebus.R
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme

@Composable
fun InfoField(
    @StringRes labelRes: Int,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = stringResource(labelRes),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview(name = "InfoField - Claro", showBackground = true)
@Composable
private fun InfoFieldLightPreview() {
    SafeBusTheme(darkTheme = false) {
        Surface {
            InfoField(
                labelRes = R.string.personal_data_name,
                value = stringResource(R.string.personal_data_preview_name)
            )
        }
    }
}

@Preview(name = "InfoField - Escuro", showBackground = true)
@Composable
private fun InfoFieldDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        Surface {
            InfoField(
                labelRes = R.string.personal_data_name,
                value = stringResource(R.string.personal_data_preview_name)
            )
        }
    }
}