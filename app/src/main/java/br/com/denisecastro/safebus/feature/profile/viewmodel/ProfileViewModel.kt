package br.com.denisecastro.safebus.feature.profile.viewmodel

import androidx.lifecycle.ViewModel
import br.com.denisecastro.safebus.feature.profile.model.ProfileUiState
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfileViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadProfile()
    }

    private fun loadProfile() {
        val user = auth.currentUser

        _uiState.value = ProfileUiState(
            name = user?.displayName.orEmpty(),
            email = user?.email.orEmpty(),
            photoUrl = user?.photoUrl?.toString()
        )
    }

    fun logout() {
        auth.signOut()
    }
}
