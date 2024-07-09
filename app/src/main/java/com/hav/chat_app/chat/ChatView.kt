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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.Alignment
import com.hav.chat_app.components.ChatBar

@Composable
fun ChatView(chatViewModel : ChatViewModel = ChatViewModel()) {
    val message: String by chatViewModel.message.observeAsState("")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LazyColumn(
            modifier = Modifier.weight(0.85f)
        ) {
            item {

            }
        }

        ChatBar(
            message = message,
            onMessageChange = { chatViewModel.updateMessage(it) },
            onSendMessage = { chatViewModel.sendMessage() }
        )

    }
}