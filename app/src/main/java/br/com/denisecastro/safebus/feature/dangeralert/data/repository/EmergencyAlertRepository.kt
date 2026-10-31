package br.com.denisecastro.safebus.feature.dangeralert.data.repository

import br.com.denisecastro.safebus.feature.dangeralert.data.model.AlertHistoryItem
import br.com.denisecastro.safebus.feature.dangeralert.data.model.EmergencyAlert
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await

class EmergencyAlertRepository(
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
) {

    suspend fun registerAlert(
        alert: EmergencyAlert
    ): Result<String> {
        return try {
            val document = firestore
                .collection("emergency_alerts")
                .document()

            val data = mapOf(
                "userId" to alert.userId,
                "type" to alert.type,
                "latitude" to alert.latitude,
                "longitude" to alert.longitude,
                "accuracyMeters" to alert.accuracyMeters,
                "status" to "REGISTERED",
                "createdAt" to FieldValue.serverTimestamp()
            )

            document.set(data).await()

            Result.success(document.id)
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }

    suspend fun getAlertHistory(
        userId: String
    ): Result<List<AlertHistoryItem>> {
        return try {
            val snapshot = firestore
                .collection("emergency_alerts")
                .whereEqualTo("userId", userId)
                .orderBy("createdAt", Query.Direction.DESCENDING)
                .get()
                .await()

            val alerts = snapshot.documents.map { document ->
                AlertHistoryItem(
                    id = document.id,
                    type = document.getString("type").orEmpty(),
                    latitude = document.getDouble("latitude") ?: 0.0,
                    longitude = document.getDouble("longitude") ?: 0.0,
                    accuracyMeters = document.getDouble("accuracyMeters"),
                    status = document.getString("status").orEmpty(),
                    createdAt = document.getTimestamp("createdAt")
                )
            }

            Result.success(alerts)
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }
}
