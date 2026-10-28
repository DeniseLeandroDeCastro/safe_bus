package br.com.denisecastro.safebus.ui.components.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Chat
import androidx.compose.material.icons.outlined.PermContactCalendar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import br.com.denisecastro.safebus.R
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme

@Composable
fun MoreMenu(
    expanded: Boolean,
    onDismiss: () -> Unit,
    onConversationsClick: () -> Unit,
    onContactsClick: () -> Unit
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismiss
    ) {
        DropdownMenuItem(
            text = {
                Text(stringResource(R.string.nav_conversations))
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.Chat,
                    contentDescription = null
                )
            },
            onClick = {
                onDismiss()
                onConversationsClick()
            }
        )

        DropdownMenuItem(
            text = {
                Text(stringResource(R.string.nav_contacts))
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.PermContactCalendar,
                    contentDescription = null
                )
            },
            onClick = {
                onDismiss()
                onContactsClick()
            }
        )
    }
}

@Preview(name = "Mais - Claro", showSystemUi = true)
@Composable
private fun MoreMenuLightPreview() {
    SafeBusTheme(darkTheme = false) {
        Surface(color = MaterialTheme.colorScheme.background) {
            MoreMenu(
                expanded = true,
                onDismiss = {},
                onConversationsClick = {},
                onContactsClick = {}
            )
        }
    }
}

@Preview(name = "Mais - Escuro", showSystemUi = true)
@Composable
private fun MoreMenuDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.background) {
            MoreMenu(
                expanded = true,
                onDismiss = {},
                onConversationsClick = {},
                onContactsClick = {}
            )
        }
    }
}
