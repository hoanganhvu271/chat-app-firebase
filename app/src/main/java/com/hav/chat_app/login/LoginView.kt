package com.hav.chat_app.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hav.chat_app.R
import com.hav.chat_app.components.CButton
import com.hav.chat_app.components.IconTextField
import com.hav.chat_app.components.PasswordTextField
import com.hav.chat_app.components.SignUpText
import com.hav.chat_app.components.TitleText

@Composable
fun LoginView(onHome : () -> Unit, onNavigateBack: () -> Unit, loginViewModel: LoginViewModel = viewModel()){

    val email : String by loginViewModel.email.observeAsState("")
    val password : String by loginViewModel.password.observeAsState("")
    val loading : Boolean by loginViewModel.loading.observeAsState(initial = false)
    val context = LocalContext.current

    Box (
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column {
            TitleText(text = "LOGIN")
            Spacer(modifier = Modifier.height(20.dp))
            IconTextField(value = email, onValueChange = {loginViewModel.updateEmail(it)}, label = "Email", R.drawable.baseline_people_24)
            PasswordTextField(value = password, onValueChange = {loginViewModel.updatePassword(it)}, label = "Password", R.drawable.baseline_lock_24)
            Spacer(modifier = Modifier.height(20.dp))

            CButton({ loginViewModel.login(onHome =  onHome , context = context) }, "Login")

            SignUpText({})
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewLoginView() {
    Box (
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){

        Column {
            TitleText(text = "USER LOGIN")
            Spacer(modifier = Modifier.height(20.dp))
            IconTextField(value = "", onValueChange = {}, label = "Email", R.drawable.baseline_people_24)
            PasswordTextField(value = "", onValueChange = {}, label = "Password", R.drawable.baseline_lock_24)
            Spacer(modifier = Modifier.height(20.dp))

            CButton({  }, "Login")

            SignUpText({})
        }
    }
}