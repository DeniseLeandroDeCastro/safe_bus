package br.com.denisecastro.safebus.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = BluePrimary,
    onPrimary = Color.White,

    primaryContainer = Color(0xFFD6EAF8),
    onPrimaryContainer = Color(0xFF083A60),

    secondary = TurquoiseSecondary,
    onSecondary = Color(0xFF003D3A),

    secondaryContainer = Color(0xFFD4F7F3),
    onSecondaryContainer = Color(0xFF004F49),

    background = LightBackground,
    onBackground = LightOnBackground,

    surface = LightSurface,
    onSurface = LightOnBackground,

    surfaceVariant = Color(0xFFEAF1F5),
    onSurfaceVariant = TextSecondary,

    outline = BorderColor,

    error = Error,
    onError = Color.White
)

private val DarkColorScheme = darkColorScheme(
    primary = DarkPrimary,
    onPrimary = Color(0xFF002F4D),

    primaryContainer = Color(0xFF164D73),
    onPrimaryContainer = Color(0xFFD6EAF8),

    secondary = Color(0xFF70DED5),
    onSecondary = Color(0xFF003D3A),

    secondaryContainer = Color(0xFF145B57),
    onSecondaryContainer = Color(0xFFD4F7F3),

    background = DarkBackground,
    onBackground = DarkOnBackground,

    surface = DarkSurface,
    onSurface = DarkOnBackground,

    surfaceVariant = Color(0xFF253B53),
    onSurfaceVariant = Color(0xFFB8C7D5),

    outline = Color(0xFF526579),

    error = Color(0xFFFF7772),
    onError = Color(0xFF4A0000)
)

@Composable
fun SafeBusTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
