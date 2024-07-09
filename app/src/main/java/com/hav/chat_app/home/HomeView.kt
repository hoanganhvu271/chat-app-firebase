package com.hav.chat_app.home

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.hav.chat_app.model.Contact

@Composable
fun HomeView(onLogout : () -> Unit, homeViewModel: HomeViewModel = HomeViewModel()) {
    LaunchedEffect(key1 = Unit) {
        homeViewModel.addContact(Contact("1", 1), onLogout)
    }

    Text(text = "Home")

}