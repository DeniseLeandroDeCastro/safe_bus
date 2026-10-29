package br.com.denisecastro.safebus.feature.dangeralert.data.repository

import br.com.denisecastro.safebus.feature.dangeralert.data.model.EmergencyAlert
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
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
}
