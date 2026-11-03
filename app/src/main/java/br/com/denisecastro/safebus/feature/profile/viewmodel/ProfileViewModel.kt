package br.com.denisecastro.safebus.feature.profile.viewmodel

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.com.denisecastro.safebus.feature.profile.data.ProfilePhotoStorage
import br.com.denisecastro.safebus.feature.profile.model.ProfileUiState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val auth = FirebaseAuth.getInstance()

    private val photoStorage = ProfilePhotoStorage(application)

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadProfile()
    }

    fun loadProfile() {
        val user = auth.currentUser ?: return

        user.reload().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val updatedUser =
                    auth.currentUser ?: return@addOnCompleteListener

                val userId = updatedUser.uid
                val firebasePhotoUrl =
                    updatedUser.photoUrl?.toString()

                _uiState.value = _uiState.value.copy(
                    name = updatedUser.displayName.orEmpty(),
                    email = updatedUser.email.orEmpty(),
                    isEmailVerified = updatedUser.isEmailVerified
                )

                viewModelScope.launch {
                    val localPhotoUrl = withContext(Dispatchers.IO) {
                        photoStorage.getPhoto(userId)
                    }

                    if (auth.currentUser?.uid == userId) {
                        _uiState.value = _uiState.value.copy(
                            photoUrl = localPhotoUrl ?: firebasePhotoUrl
                        )
                    }
                }
            }
        }
    }

    fun updatePhoto(uri: Uri) {
        val userId = auth.currentUser?.uid ?: return

        viewModelScope.launch {
            try {
                val photoUrl = withContext(Dispatchers.IO) {
                    photoStorage.savePhoto(uri, userId)
                }

                if (auth.currentUser?.uid == userId) {
                    _uiState.value = _uiState.value.copy(
                        photoUrl = photoUrl
                    )
                }
            } catch (exception: Exception) {
                // Mantém a foto anterior se não conseguir salvar.
            }
        }
    }

    fun updateName(name: String) {
        val newName = name.trim()
        val user = auth.currentUser

        if (newName.isBlank() || user == null || _uiState.value.isSaving) {
            return
        }

        _uiState.value = _uiState.value.copy(
            isSaving = true,
            nameUpdateSuccess = false,
            nameUpdateError = false
        )

        val profileUpdates = UserProfileChangeRequest.Builder()
            .setDisplayName(newName)
            .build()

        user.updateProfile(profileUpdates)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _uiState.value = _uiState.value.copy(
                        name = newName,
                        isSaving = false,
                        nameUpdateSuccess = true
                    )
                } else {
                    _uiState.value = _uiState.value.copy(
                        isSaving = false,
                        nameUpdateError = true
                    )
                }
            }
    }

    fun clearNameUpdateResult() {
        _uiState.value = _uiState.value.copy(
            nameUpdateSuccess = false,
            nameUpdateError = false
        )
    }

    fun logout() {
        auth.signOut()
    }
}
