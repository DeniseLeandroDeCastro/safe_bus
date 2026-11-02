package br.com.denisecastro.safebus.feature.profile.components.fields

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import br.com.denisecastro.safebus.R
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme

@Composable
fun NameEditField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        enabled = enabled,
        singleLine = true,
        label = {
            Text(stringResource(R.string.personal_data_name))
        },
        shape = MaterialTheme.shapes.large
    )
}

@Preview(name = "Nome - Claro", showBackground = true)
@Composable
private fun NameEditFieldLightPreview() {
    SafeBusTheme(darkTheme = false) {
        Surface {
            NameEditField(
                value = stringResource(R.string.personal_data_preview_name),
                onValueChange = {}
            )
        }
    }
}

@Preview(name = "Nome - Escuro", showBackground = true)
@Composable
private fun NameEditFieldDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        Surface {
            NameEditField(
                value = stringResource(R.string.personal_data_preview_name),
                onValueChange = {}
            )
        }
    }
}