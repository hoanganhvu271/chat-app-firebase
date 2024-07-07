package com.hav.chat_app

import androidx.navigation.NavController
import com.hav.chat_app.Destination.AUTHEN_SCREEN

object Destination{
    const val AUTHEN_SCREEN = "authen"
    const val LOGIN_SCREEN = "login"
    const val REGISTER_SCREEN = "register"
    const val HOME_SCREEN = "home"
}

class Navigate(val navController: NavController){
    val home: () -> Unit = {
        navController.navigate(Destination.HOME_SCREEN){
            popUpTo(AUTHEN_SCREEN) {
                inclusive = true
            }
            popUpTo(Destination.REGISTER_SCREEN) {
                inclusive = true
            }
        }
    }
    val login: () -> Unit = { navController.navigate(Destination.LOGIN_SCREEN) }
    val register: () -> Unit = { navController.navigate(Destination.REGISTER_SCREEN) }
    val navigateBack: () -> Unit = { navController.popBackStack() }
}