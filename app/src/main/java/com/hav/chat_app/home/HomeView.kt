package com.hav.chat_app.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hav.chat_app.components.ContactBox
import com.hav.chat_app.components.MessageBox
import com.hav.chat_app.model.Contact

@Composable
fun HomeView(onLogout : () -> Unit, homeViewModel: HomeViewModel = HomeViewModel()) {

    val contactList : List<Contact> by homeViewModel.contacts.observeAsState(initial = emptyList())

    LaunchedEffect(key1 = Unit) {
//        homeViewModel.addContact(Contact("1", 1), onLogout)
        homeViewModel.getContacts()
    }

    Column {
        Text(text = "Home View")
        LazyColumn(
            modifier = Modifier.padding(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            reverseLayout = true
        ) {
            items(contactList) {contact ->
               ContactBox(contact = contact)
            }
        }
    }
}