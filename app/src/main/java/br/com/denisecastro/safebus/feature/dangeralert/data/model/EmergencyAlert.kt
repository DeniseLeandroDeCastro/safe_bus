package br.com.denisecastro.safebus.feature.dangeralert.data.model

data class EmergencyAlert(
    val userId: String,
    val type: String,
    val latitude: Double,
    val longitude: Double,
    val accuracyMeters: Double?
)
