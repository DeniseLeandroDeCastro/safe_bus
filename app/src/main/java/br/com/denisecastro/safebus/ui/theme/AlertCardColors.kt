package br.com.denisecastro.safebus.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

data class AlertCardColors(
    val background: Color,
    val accent: Color,
    val content: Color
)

object SafeBusAlertColors {

    @Composable
    fun danger(
        darkTheme: Boolean = isSystemInDarkTheme()
    ): AlertCardColors {
        return if (darkTheme) {
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
    fun harassment(
        darkTheme: Boolean = isSystemInDarkTheme()
    ): AlertCardColors {
        return if (darkTheme) {
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
