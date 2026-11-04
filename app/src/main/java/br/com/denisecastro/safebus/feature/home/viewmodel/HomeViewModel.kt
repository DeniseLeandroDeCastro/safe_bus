package br.com.denisecastro.safebus.feature.home.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.com.denisecastro.safebus.feature.home.model.HomeUiState
import br.com.denisecastro.safebus.feature.profile.data.ProfilePhotoStorage
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val auth = FirebaseAuth.getInstance()

    private val photoStorage = ProfilePhotoStorage(application)

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun loadUserProfile() {
        val user = auth.currentUser ?: return

        val userId = user.uid
        val firebasePhotoUrl = user.photoUrl?.toString()

        viewModelScope.launch {
            val localPhotoUrl = withContext(Dispatchers.IO) {
                photoStorage.getPhoto(userId)
            }

            if (auth.currentUser?.uid == userId) {
                _uiState.value = _uiState.value.copy(
                    userName = user.displayName.orEmpty(),
                    photoUrl = localPhotoUrl ?: firebasePhotoUrl
                )
            }
        }
    }
}
