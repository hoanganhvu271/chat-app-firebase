package com.hav.chat_app.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.Query
import com.hav.chat_app.model.Contact
import kotlin.time.Duration

class HomeViewModel {

    private val firestore = FirebaseFirestore.getInstance()
    private val userId = FirebaseAuth.getInstance().currentUser?.uid ?: "PVNTpcBvrQgzDibuSmJJygfjKRy1"
    private val contactsCollection = firestore.collection("contacts")



    private val _contacts = MutableLiveData<List<Contact>>()
    val contacts : LiveData<List<Contact>> = _contacts

    fun updateContacts(contacts: List<Contact>) {
        _contacts.value = contacts
    }

    fun updateTimestamp(contact: Contact, timestamp: Long) {
        // update timestamp
    }



    fun addContact(contact: Contact, onBack: () -> Unit){

        Log.d("Vu", "addContact")
        val contactMap = hashMapOf(
            "userId" to contact.userId,
            "lastContact" to contact.lastContactTimeStamp,
        )

        firestore.collection("users")
            .document(userId)
            .collection("contacts")
            .add(contactMap)
            .addOnSuccessListener {
                Log.d("Vu", "DocumentSnapshot added with ID: ${it.id}")
            }
            .addOnFailureListener { e ->
                Log.w("Vu", "Error adding document", e)
            }
    }



    fun getContacts() {
        // get contacts

        firestore.collection("users").document(userId).collection("contacts")
            .orderBy("lastContact", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { result ->
                val contactsList : List<Contact> = result.documents.map { document ->
                    val userIdField = document.getString("userId") ?: ""
                    val lastContactField = document.getLong("lastContact") ?: 0
                    Contact(userIdField, lastContactField)
                }
                updateContacts(contactsList)
            }
    }

    fun deleteContact(contact: Contact) {

    }
}