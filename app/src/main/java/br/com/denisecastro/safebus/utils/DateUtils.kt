package br.com.denisecastro.safebus.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateUtils {

    fun formatDateTime(date: Date): String {
        return SimpleDateFormat(
            "dd/MM/yyyy 'às' HH:mm",
            Locale.forLanguageTag("pt-BR")
        ).format(date)
    }
}