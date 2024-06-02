package com.shifat.myhadis.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shifat.myhadis.model.Contact
import com.shifat.myhadis.repository.AuthRepository
import com.shifat.myhadis.repository.ContactsRepository
import com.shifat.myhadis.repository.HadisRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ContactsViewModel @Inject constructor(
    private val repository: ContactsRepository,
    private val authRepository: AuthRepository
): ViewModel(){

    val userNumber = authRepository.userNumber.value
    val contactList: StateFlow<List<Contact>>
        get() = repository.contacts


    val isLoading = MutableStateFlow(false)
    val error = MutableStateFlow<String>("")
    init {
        viewModelScope.launch {
            try {
                error.value = ""
                isLoading.value = true
                repository.getContacts()
            }catch (e: Exception){
                error.value = e.message.toString()
            }finally {
                isLoading.value = false
            }
        }
    }
    fun addContact(name: String, phoneNumber:String) {
        viewModelScope.launch {
            error.value = ""
            val contact = Contact(
                userNumber = userNumber,
                name = name,
                number = phoneNumber
            )

            try {
                isLoading.value = true
                repository.addContact(contact)
            }catch (e: Exception){
                error.value = e.message.toString()
            }finally {
                isLoading.value = false
            }
        }
    }

    fun updateContact(oldContact: Contact, newContact: Contact) {
        viewModelScope.launch {
            try {
                error.value = ""
                isLoading.value = true
                repository.updateContact(oldContact, newContact)
            }catch (e: Exception){
                error.value = e.message.toString()
            }finally {
                isLoading.value = false
            }
        }
    }

    fun removeContact(contact: Contact) {
        viewModelScope.launch {
            try {
                error.value = ""
                isLoading.value = true
                repository.removeContact(contact)
            }catch (e: Exception){
                error.value = e.message.toString()
            }finally {
                isLoading.value = false
            }
        }
    }

    fun resetError() {
        error.value = ""
    }

}


