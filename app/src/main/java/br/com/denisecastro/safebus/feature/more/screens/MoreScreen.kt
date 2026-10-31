package br.com.denisecastro.safebus.feature.more.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.Contacts
import androidx.compose.material.icons.outlined.History
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
import br.com.denisecastro.safebus.feature.profile.components.cards.SectionCard
import br.com.denisecastro.safebus.feature.profile.components.optionrow.OptionRow
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme

@Composable
fun MoreScreen(
    onAlertHistoryClick: () -> Unit,
    onConversationsClick: () -> Unit,
    onContactsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.navigationBars)
                .verticalScroll(rememberScrollState())
                .padding(
                    horizontal = 20.dp,
                    vertical = 24.dp
                ),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                text = stringResource(R.string.more_title),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onBackground
            )

            SectionCard(
                titleRes = R.string.more_records_section
            ) {
                OptionRow(
                    titleRes = R.string.more_alert_history,
                    descriptionRes = R.string.more_alert_history_description,
                    icon = Icons.Outlined.History,
                    onClick = onAlertHistoryClick
                )
            }

            SectionCard(
                titleRes = R.string.more_communication_section
            ) {
                OptionRow(
                    titleRes = R.string.more_conversations,
                    descriptionRes = R.string.more_conversations_description,
                    icon = Icons.Outlined.ChatBubbleOutline,
                    onClick = onConversationsClick
                )

                HorizontalDivider()

                OptionRow(
                    titleRes = R.string.more_contacts,
                    descriptionRes = R.string.more_contacts_description,
                    icon = Icons.Outlined.Contacts,
                    onClick = onContactsClick
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Preview(
    name = "Mais - Tema claro",
    showSystemUi = true
)
@Composable
private fun MoreScreenLightPreview() {
    SafeBusTheme(darkTheme = false) {
        MoreScreen(
            onAlertHistoryClick = {},
            onConversationsClick = {},
            onContactsClick = {}
        )
    }
}

@Preview(
    name = "Mais - Tema escuro",
    showSystemUi = true
)
@Composable
private fun MoreScreenDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        MoreScreen(
            onAlertHistoryClick = {},
            onConversationsClick = {},
            onContactsClick = {}
        )
    }
}
