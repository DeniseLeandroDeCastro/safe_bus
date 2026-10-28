package br.com.denisecastro.safebus.ui.components.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material.icons.outlined.MoreHoriz
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import br.com.denisecastro.safebus.R
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme

enum class SafeBusBottomDestination(
    @StringRes val labelRes: Int,
    val icon: ImageVector
) {
    HOME(R.string.nav_home, Icons.Outlined.Home),
    MAP(R.string.nav_map, Icons.Outlined.Map),
    PROFILE(R.string.nav_profile, Icons.Outlined.PersonOutline),
    MORE(R.string.nav_more, Icons.Outlined.MoreHoriz)
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
                onClick = { onDestinationClick(destination) },
                icon = {
                    Icon(
                        imageVector = destination.icon,
                        contentDescription = null
                    )
                },
                label = {
                    Text(stringResource(destination.labelRes))
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

@Preview(name = "Navegação inferior - Claro", showSystemUi = true)
@Composable
private fun SafeBusBottomBarLightPreview() {
    SafeBusTheme(darkTheme = false) {
        Surface {
            SafeBusBottomBar(
                selectedDestination = SafeBusBottomDestination.HOME,
                onDestinationClick = {}
            )
        }
    }
}

@Preview(name = "Navegação inferior - Escuro", showSystemUi = true)
@Composable
private fun SafeBusBottomBarDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        Surface {
            SafeBusBottomBar(
                selectedDestination = SafeBusBottomDestination.HOME,
                onDestinationClick = {}
            )
        }
    }
}
