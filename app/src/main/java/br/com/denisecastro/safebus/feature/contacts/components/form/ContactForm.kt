package br.com.denisecastro.safebus.feature.contacts.components.form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.PeopleOutline
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.denisecastro.safebus.R
import br.com.denisecastro.safebus.feature.welcome.components.fields.StandardTextField
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme

@Composable
fun ContactForm(
    name: String,
    onNameChange: (String) -> Unit,
    phone: String,
    onPhoneChange: (String) -> Unit,
    relationship: String,
    onRelationshipChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        StandardTextField(
            value = name,
            onValueChange = onNameChange,
            label = stringResource(R.string.contact_name),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.PersonOutline,
                    contentDescription = null
                )
            }
        )

        StandardTextField(
            value = phone,
            onValueChange = onPhoneChange,
            label = stringResource(R.string.contact_phone),
            keyboardType = KeyboardType.Phone,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Call,
                    contentDescription = null
                )
            }
        )

        StandardTextField(
            value = relationship,
            onValueChange = onRelationshipChange,
            label = stringResource(R.string.contact_relationship),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.PeopleOutline,
                    contentDescription = null
                )
            }
        )
    }
}

@Preview(name = "Formulário de contato - Claro", showBackground = true)
@Composable
private fun ContactFormLightPreview() {
    SafeBusTheme(darkTheme = false) {
        Surface(color = MaterialTheme.colorScheme.background) {
            ContactForm(
                name = "",
                onNameChange = {},
                phone = "",
                onPhoneChange = {},
                relationship = "",
                onRelationshipChange = {},
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Preview(name = "Formulário de contato - Escuro", showBackground = true)
@Composable
private fun ContactFormDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.background) {
            ContactForm(
                name = "Maria Silva",
                onNameChange = {},
                phone = "(84) 99999-9999",
                onPhoneChange = {},
                relationship = "Mãe",
                onRelationshipChange = {},
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
