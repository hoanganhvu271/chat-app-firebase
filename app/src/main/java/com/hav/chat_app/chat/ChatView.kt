package com.hav.chat_app.chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import com.google.firebase.auth.FirebaseAuth
import com.hav.chat_app.components.ChatBar
import com.hav.chat_app.components.MessageBox
import com.hav.chat_app.model.Message

@Composable
fun ChatView(chatViewModel : ChatViewModel = ChatViewModel()) {
    val message: String by chatViewModel.message.observeAsState("")
    val messages: List<Message> by chatViewModel.messages.observeAsState(listOf())
    val context = LocalContext.current
    val currentUserId = FirebaseAuth.getInstance().currentUser?.uid

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LazyColumn(
            modifier = Modifier.weight(0.85f),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            reverseLayout = true
        ) {
            items(messages) {m ->
                MessageBox(message = m, if (m.senderId == currentUserId) true else false)
            }
        }

        ChatBar(
            message = message,
            onMessageChange = { chatViewModel.updateMessage(it) },
            onSendMessage = { chatViewModel.sendMessage() }
        )

    }
}