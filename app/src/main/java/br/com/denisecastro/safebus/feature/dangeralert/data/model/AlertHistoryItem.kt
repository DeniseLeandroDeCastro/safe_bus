package br.com.denisecastro.safebus.feature.dangeralert.data.model

import com.google.firebase.Timestamp

data class AlertHistoryItem(
    val id: String,
    val type: String,
    val latitude: Double,
    val longitude: Double,
    val accuracyMeters: Double?,
    val status: String,
    val createdAt: Timestamp?
)
