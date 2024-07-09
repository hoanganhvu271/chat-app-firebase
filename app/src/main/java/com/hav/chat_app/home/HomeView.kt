package com.hav.chat_app.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun HomeView(onLogout : () -> Unit) {
    Text(text = "Home")
}