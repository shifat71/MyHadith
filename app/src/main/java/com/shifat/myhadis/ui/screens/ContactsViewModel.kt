package com.shifat.myhadis.ui.screens

import androidx.lifecycle.ViewModel
import com.shifat.myhadis.model.Contact
import com.shifat.myhadis.repository.HadisRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class ContactsViewModel @Inject constructor(private val repository: HadisRepository): ViewModel(){
    private val _contacts = MutableStateFlow<List<Contact>>(listOf(
        Contact(1,"Shifat", "01872583391"),
        Contact(2,"Hasan", "01872583391"),
    ))
    val contacts: StateFlow<List<Contact>> = _contacts

    fun addContact(contact: Contact) {
        _contacts.value = _contacts.value + contact
    }

    fun updateContact(oldContact: Contact, newContact: Contact) {
        _contacts.value = _contacts.value.map { if (it == oldContact) newContact else it }
    }

    fun removeContact(contact: Contact) {
        _contacts.value = _contacts.value - contact
    }
}


