package br.com.denisecastro.safebus.feature.profile.viewmodel

import androidx.lifecycle.ViewModel
import br.com.denisecastro.safebus.feature.profile.model.ProfileUiState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfileViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadProfile()
    }

    fun loadProfile() {
        val user = auth.currentUser

        _uiState.value = _uiState.value.copy(
            name = user?.displayName.orEmpty(),
            email = user?.email.orEmpty(),
            photoUrl = user?.photoUrl?.toString(),
            isEmailVerified = user?.isEmailVerified == true
        )
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