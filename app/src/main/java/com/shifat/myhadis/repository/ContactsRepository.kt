package com.shifat.myhadis.repository

import android.util.Log
import com.shifat.myhadis.api.DeleteContactRequest
import com.shifat.myhadis.api.HadisApi
import com.shifat.myhadis.api.SaveContactRequest
import com.shifat.myhadis.api.UpdateContactRequest
import com.shifat.myhadis.model.Contact
import com.shifat.myhadis.model.Hadis
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class ContactsRepository @Inject constructor(
    private val hadisApi: HadisApi,
    private val authRepository: AuthRepository
){
    val userNumber = authRepository.userNumber.value

    private val _contacts = MutableStateFlow<List<Contact>>(
        listOf(
            Contact("01872583391", "Shifat", "01872583391"))
    )
    val contacts: StateFlow<List<Contact>>
        get() = _contacts

    suspend fun getContacts(){
        val response = hadisApi.getContacts(userNumber)
        Log.d("ContactsRep", response.body().toString())
        if(response.isSuccessful && response.body()!=null){
            _contacts.emit(response.body()!!)
        }
    }

    suspend fun addContact(contact: Contact) {
        val saveContactRequest = SaveContactRequest(
            mobile= contact.userNumber,
            name= contact.name,
            favMobile= contact.number
        )
        Log.d("AddContactReq", "started")
        val response = hadisApi.saveContact(userNumber, saveContactRequest)
        Log.d("AddContactReq", "done")
        if(response.isSuccessful && response.code()==201){
            _contacts.value = _contacts.value + contact
        }else throw Exception("Couldn't Add Contact! The user might be existing or a server error.")
    }

   suspend fun updateContact(oldContact: Contact, newContact: Contact) {

           val updateContactRequest = UpdateContactRequest(
               mobile= userNumber,
               favMobile= oldContact.number,
               editedFavMobile= newContact.number,
               editedName= newContact.name
           )
           Log.d("UpdateContactReq", "started")
           val response = hadisApi.updateContact(userNumber, updateContactRequest)
           Log.d("UpdateContactReq", "done")

           if(response.isSuccessful && response.code()==201) {
               _contacts.value = _contacts.value.map {
                   if (it == oldContact) newContact else it
               }
           }else throw Exception("Couldn't Update Contact! Please Try Again.")

    }

   suspend fun removeContact(contact: Contact) {
        val deleteContactRequest = DeleteContactRequest(
            favMobile= contact.number
        )

       Log.d("DeleteContactReq", "started")
       val response = hadisApi.deleteContact(userNumber,deleteContactRequest)
       Log.d("DeleteContactReq", "done")

       if(response.isSuccessful && response.code()==201) {
            _contacts.value = _contacts.value - contact
       }else throw Exception("Couldn't Delete Contact! Please Try Again.")
    }

}