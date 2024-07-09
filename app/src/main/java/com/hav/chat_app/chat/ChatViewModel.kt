package com.hav.chat_app.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ChatViewModel {

    private val _message = MutableLiveData<String>()
    val message : LiveData<String> = _message


    fun updateMessage(message: String) {
        _message.value = message
    }

    fun sendMessage() {
        // send message
    }

    fun receiveMessage() {
        // receive message
    }
}