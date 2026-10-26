package br.com.denisecastro.safebus.ui.components.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Chat
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material.icons.outlined.MoreHoriz
import androidx.compose.material.icons.outlined.PermContactCalendar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme

enum class SafeBusBottomDestination(
    val label: String,
    val icon: ImageVector
) {
    HOME(
        label = "Início",
        icon = Icons.Outlined.Home
    ),
    MAP(
        label = "Mapa",
        icon = Icons.Outlined.Map
    ),
    CONVERSATIONS(
        label = "Conversas",
        icon = Icons.AutoMirrored.Outlined.Chat
    ),
    CONTACTS(
        label = "Contatos",
        icon = Icons.Outlined.PermContactCalendar
    ),
    MORE(
        label = "Mais",
        icon = Icons.Outlined.MoreHoriz
    )
}

@Composable
fun SafeBusBottomBar(
    selectedDestination: SafeBusBottomDestination,
    onDestinationClick: (SafeBusBottomDestination) -> Unit
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        SafeBusBottomDestination.entries.forEach { destination ->
            NavigationBarItem(
                selected = selectedDestination == destination,
                onClick = {
                    onDestinationClick(destination)
                },
                icon = {
                    Icon(
                        imageVector = destination.icon,
                        contentDescription = null
                    )
                },
                label = {
                    Text(destination.label)
                },
                alwaysShowLabel = true,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        }
    }
}

@Preview(
    name = "Navegação inferior - Tema claro",
    showBackground = true
)
@Composable
private fun SafeBusBottomBarLightPreview() {
    SafeBusTheme(darkTheme = false) {
        SafeBusBottomBar(
            selectedDestination = SafeBusBottomDestination.HOME,
            onDestinationClick = {}
        )
    }
}

@Preview(
    name = "Navegação inferior - Tema escuro",
    showBackground = true
)
@Composable
private fun SafeBusBottomBarDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        SafeBusBottomBar(
            selectedDestination = SafeBusBottomDestination.HOME,
            onDestinationClick = {}
        )
    }
}
