package br.com.denisecastro.safebus.ui.components.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MainScreenScaffold(
    selectedDestination: SafeBusBottomDestination,
    onDestinationClick: (SafeBusBottomDestination) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier,
        bottomBar = {
            SafeBusBottomBar(
                selectedDestination = selectedDestination,
                onDestinationClick = onDestinationClick
            )
        },
        content = content
    )
}
