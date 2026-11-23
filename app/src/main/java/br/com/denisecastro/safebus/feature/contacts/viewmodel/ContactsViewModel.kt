package br.com.denisecastro.safebus.feature.contacts.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.denisecastro.safebus.feature.contacts.data.model.Contact
import br.com.denisecastro.safebus.feature.contacts.data.model.ContactUiState
import br.com.denisecastro.safebus.feature.contacts.data.repository.ContactRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ContactsViewModel : ViewModel() {

    private val repository = ContactRepository()
    private val auth = FirebaseAuth.getInstance()

    // Estado do formulário
    private val _uiState = MutableStateFlow(ContactUiState())
    val uiState: StateFlow<ContactUiState> = _uiState.asStateFlow()

    // Lista de contatos cadastrados
    private val _contacts = MutableStateFlow<List<Contact>>(emptyList())
    val contacts: StateFlow<List<Contact>> = _contacts.asStateFlow()

    // Estado de carregamento da lista
    private val _isLoadingContacts = MutableStateFlow(false)
    val isLoadingContacts: StateFlow<Boolean> = _isLoadingContacts.asStateFlow()

    // Mensagem de erro da consulta
    private val _contactsError = MutableStateFlow<String?>(null)
    val contactsError: StateFlow<String?> = _contactsError.asStateFlow()

    fun onNameChange(value: String) {
        _uiState.update {
            it.copy(name = value, errorMessage = null)
        }
    }

    fun onPhoneChange(value: String) {
        _uiState.update {
            it.copy(phone = value, errorMessage = null)
        }
    }

    fun onRelationshipChange(value: String) {
        _uiState.update {
            it.copy(relationship = value, errorMessage = null)
        }
    }

    fun saveContact(onSuccess: () -> Unit) {
        val state = _uiState.value

        if (!state.isFormValid || state.isLoading) return

        val userId = auth.currentUser?.uid

        if (userId == null) {
            _uiState.update {
                it.copy(errorMessage = "Usuário não autenticado.")
            }
            return
        }

        _uiState.update {
            it.copy(
                isLoading = true,
                errorMessage = null
            )
        }

        viewModelScope.launch {
            val contact = Contact(
                userId = userId,
                name = state.name.trim(),
                phone = state.phone.trim(),
                relationship = state.relationship.trim()
            )

            val result = repository.addContact(contact)

            result.onSuccess {
                _uiState.update {
                    it.copy(isLoading = false)
                }

                onSuccess()
            }

            result.onFailure {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = "Não foi possível salvar o contato. Tente novamente."
                    )
                }
            }
        }
    }

    fun loadContacts() {
        val userId = auth.currentUser?.uid

        if (userId == null) {
            _contactsError.value = "Usuário não autenticado."
            return
        }

        viewModelScope.launch {
            _isLoadingContacts.value = true
            _contactsError.value = null

            val result = repository.getContacts(userId)

            result.onSuccess { contactList ->
                _contacts.value = contactList.sortedBy {
                    it.name.lowercase()
                }
            }

            result.onFailure {
                _contactsError.value =
                    "Não foi possível carregar os contatos."
            }

            _isLoadingContacts.value = false
        }
    }
}
