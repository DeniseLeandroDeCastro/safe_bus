package br.com.denisecastro.safebus.ui.components.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme

@Composable
fun MainScreenScaffold(
    selectedDestination: SafeBusBottomDestination,
    onDestinationClick: (SafeBusBottomDestination) -> Unit,
    modifier: Modifier = Modifier,
    onConversationsClick: () -> Unit = {},
    onContactsClick: () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    var showMoreMenu by remember {
        mutableStateOf(false)
    }

    Scaffold(
        modifier = modifier,
        bottomBar = {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                SafeBusBottomBar(
                    selectedDestination = selectedDestination,
                    onDestinationClick = { destination ->
                        if (destination == SafeBusBottomDestination.MORE) {
                            showMoreMenu = !showMoreMenu
                        } else {
                            showMoreMenu = false
                            onDestinationClick(destination)
                        }
                    }
                )

                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .offset(y = (-8).dp)
                ) {
                    MoreMenu(
                        expanded = showMoreMenu,
                        onDismiss = { showMoreMenu = false },
                        onConversationsClick = onConversationsClick,
                        onContactsClick = onContactsClick
                    )
                }
            }
        },
        content = content
    )
}

@Preview(name = "Navegação - Claro", showSystemUi = true)
@Composable
private fun MainScreenScaffoldLightPreview() {
    SafeBusTheme(darkTheme = false) {
        Surface {
            MainScreenScaffold(
                selectedDestination = SafeBusBottomDestination.HOME,
                onDestinationClick = {}
            ) { }
        }
    }
}

@Preview(name = "Navegação - Escuro", showSystemUi = true)
@Composable
private fun MainScreenScaffoldDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        Surface {
            MainScreenScaffold(
                selectedDestination = SafeBusBottomDestination.HOME,
                onDestinationClick = {}
            ) { }
        }
    }
}
