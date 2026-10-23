package br.com.denisecastro.safebus.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.MaterialTheme

data class AlertCardColors(
    val background: Color,
    val accent: Color,
    val content: Color
)

object SafeBusAlertColors {

    @Composable
    fun danger(): AlertCardColors {
        val isDarkTheme =
            MaterialTheme.colorScheme.background == DarkBackground

        return if (isDarkTheme) {
            AlertCardColors(
                background = DangerBackgroundDark,
                accent = DangerAccentDark,
                content = DangerContentDark
            )
        } else {
            AlertCardColors(
                background = DangerBackgroundLight,
                accent = DangerAccentLight,
                content = DangerContentLight
            )
        }
    }

    @Composable
    fun harassment(): AlertCardColors {
        val isDarkTheme =
            MaterialTheme.colorScheme.background == DarkBackground

        return if (isDarkTheme) {
            AlertCardColors(
                background = HarassmentBackgroundDark,
                accent = HarassmentAccentDark,
                content = HarassmentContentDark
            )
        } else {
            AlertCardColors(
                background = HarassmentBackgroundLight,
                accent = HarassmentAccentLight,
                content = HarassmentContentLight
            )
        }
    }
}
