package com.hav.chat_app.chat

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.hav.chat_app.model.Message

class ChatViewModel {

    private val fireStore = FirebaseFirestore.getInstance()
    private val messagesCollection = fireStore.collection("messages")

    private val _message = MutableLiveData<String>()
    val message : LiveData<String> = _message

    private val _messages = MutableLiveData<List<Message>>()
    val messages : LiveData<List<Message>> = _messages


    fun updateMessage(message: String) {
        _message.value = message
    }



    fun sendMessage(newMessage: Message) {
        // send message
        val messageMap = hashMapOf(
            "senderId" to newMessage.senderId,
            "receiverId" to newMessage.receiverId,
            "content" to newMessage.content,
            "timestamp" to newMessage.timestamp,
            "isRead" to newMessage.isRead
        )

        messagesCollection.add(messageMap)
            .addOnSuccessListener {
                // success
                Log.d("Vu", "DocumentSnapshot added with ID: ${it.id}")
            }
            .addOnFailureListener { e ->
                // fail
                Log.w("Vu", "Error adding document", e)
            }

        getMessages(newMessage.receiverId)
    }


    fun getMessages(chatId: String) {
        // get messages
        messagesCollection
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    Log.w("Vu", "Error getting documents.", error)
                    return@addSnapshotListener
                }

                if (snapshot != null) {
                    val messages = mutableListOf<Message>()
                    for (document in snapshot.documents) {
                        Log.d("Vu", "${document.id} => ${document.data}")
                        val message = Message(
                            document.getString("senderId") ?: "",
                            document.getString("receiverId") ?: "",
                            document.getString("content") ?: "",
                            document.getLong("timestamp") ?: 0,
                            document.getBoolean("isRead") ?: false
                        )
                        messages.add(message)
                    }
                    _messages.value = messages
                } else {
                    Log.d("Vu", "Current data: null")
                }
            }

    }
}