package br.com.denisecastro.safebus.feature.contacts.data.repository

import br.com.denisecastro.safebus.feature.contacts.data.model.Contact
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class ContactRepository(
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
) {

    suspend fun addContact(
        contact: Contact
    ): Result<String> {
        return try {
            val document = firestore
                .collection("contacts")
                .document()

            val data = mapOf(
                "userId" to contact.userId,
                "name" to contact.name,
                "phone" to contact.phone,
                "relationship" to contact.relationship
            )

            document.set(data).await()

            Result.success(document.id)
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }

    suspend fun getContacts(
        userId: String
    ): Result<List<Contact>> {
        return try {
            val snapshot = firestore
                .collection("contacts")
                .whereEqualTo("userId", userId)
                .get()
                .await()

            val contacts = snapshot.documents.map { document ->
                Contact(
                    id = document.id,
                    userId = document.getString("userId").orEmpty(),
                    name = document.getString("name").orEmpty(),
                    phone = document.getString("phone").orEmpty(),
                    relationship = document.getString("relationship").orEmpty()
                )
            }

            Result.success(contacts)
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }
}
