package com.hav.chat_app.login

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth


class LoginViewModel : ViewModel() {
    private val auth : FirebaseAuth = FirebaseAuth.getInstance()

    private val _email = MutableLiveData("")
    val email: LiveData<String> = _email

    private val _password = MutableLiveData("")
    val password: LiveData<String> = _password

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    fun updateEmail(email: String) {
        _email.value = email
    }

    fun updatePassword(password: String) {
        _password.value = password
    }

    fun login(onHome: () -> Unit, context: Context) {
//        Log.d("Vu", "123")
//        onHome()
        if (_loading.value == true) return

        val emailValue = _email.value
        val passwordValue = _password.value

        Toast.makeText(context, "Email: $emailValue, Password: $passwordValue", Toast.LENGTH_SHORT).show()

        if (emailValue.isNullOrBlank() || passwordValue.isNullOrBlank()) {
            Toast.makeText(context, "Email or Password is empty", Toast.LENGTH_SHORT).show()
            return
        }

        _loading.value = true
//        Log.d("Vu", "...signIn")
        auth.signInWithEmailAndPassword(emailValue, passwordValue)
            .addOnCompleteListener { task ->
                _loading.value = false
                if (task.isSuccessful) {
                    Log.d("Vu", "signIn")
                    onHome()
                } else {
                    Log.e("Vu", "signInWithEmailAndPassword:failure", task.exception)
                    Toast.makeText(context, "Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
