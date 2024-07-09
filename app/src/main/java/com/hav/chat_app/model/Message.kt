package com.hav.chat_app.model

class Message(val senderId: String, val receiverId: String, val content: String, val timestamp: Long, val isRead: Boolean) {

}