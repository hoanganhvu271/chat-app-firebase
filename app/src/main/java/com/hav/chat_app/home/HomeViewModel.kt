package com.hav.chat_app.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.hav.chat_app.model.Contact

class HomeViewModel {

    private val firestore = FirebaseFirestore.getInstance()
    private val contactsCollection = firestore.collection("contacts")
    private val _contacts = MutableLiveData<List<Contact>>()
    val contacts : LiveData<List<Contact>> = _contacts

    fun updateContacts(contacts: List<Contact>) {
        _contacts.value = contacts
    }

    fun addContact(contact: Contact, onBack: () -> Unit){
        Log.d("Vu", "addContact")
        contactsCollection.add(contact)
            .addOnSuccessListener { documentReference ->
                onBack()
                Log.d("Vu", "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w("Vu", "Error adding document", e)
            }
    }

    fun getContacts() {
        // get contacts
    }

    fun deleteContact(contact: Contact) {

    }
}