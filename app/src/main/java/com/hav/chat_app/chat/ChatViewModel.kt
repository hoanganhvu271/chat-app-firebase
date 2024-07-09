package com.hav.chat_app.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hav.chat_app.model.Message

class ChatViewModel {

    private val _message = MutableLiveData<String>()
    val message : LiveData<String> = _message

    private val _messages = MutableLiveData<List<Message>>()
    val messages : LiveData<List<Message>> = _messages


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