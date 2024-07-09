package com.hav.chat_app.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.magnifier
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hav.chat_app.components.CButton
import com.hav.chat_app.components.CTextField
import com.hav.chat_app.components.TitleText

@Composable
fun LoginView(onHome : () -> Unit, onNavigateBack: () -> Unit, loginViewModel: LoginViewModel = viewModel()){

    val email : String by loginViewModel.email.observeAsState("")
    val password : String by loginViewModel.password.observeAsState("")
    val loading : Boolean by loginViewModel.loading.observeAsState(initial = false)
    val context = LocalContext.current

    Box (
        modifier = Modifier.fillMaxSize(),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ){
//        if(loading){
//            // Loading
//            CircularProgressIndicator()
//        }

        Column {
            TitleText(text = "Chat App")
            Spacer(modifier = Modifier.height(20.dp))
            CTextField(value = email, onValueChange = {loginViewModel.updateEmail(it)}, label = "Email", keyboardType = KeyboardType.Email)
            CTextField(value = password, onValueChange = {loginViewModel.updatePassword(it)}, label = "Password", keyboardType = KeyboardType.Password)
            Spacer(modifier = Modifier.height(20.dp))

//            CButton({loginViewModel.login(onHome, context)}, "Login")
            CButton({ loginViewModel.login(onHome =  onHome , context = context) }, "Login")


        }


    }
}

