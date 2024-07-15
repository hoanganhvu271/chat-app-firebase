package com.hav.chat_app

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.firebase.auth.FirebaseAuth
import com.hav.chat_app.chat.ChatView
import com.hav.chat_app.home.HomeView
import com.hav.chat_app.login.LoginView
import com.hav.chat_app.register.RegisterView
import com.hav.chat_app.ui.theme.ChatappTheme

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    //navController is passed as a key to remember, which means the Action instance will only be recreated if navController changes.
    val action = remember(navController) {
        Navigate(navController)
    }

    ChatappTheme {
        NavHost(
            navController = navController,
            startDestination =
            if (FirebaseAuth.getInstance().currentUser != null) {
                Log.d("Vu", "AppNavigation: ${FirebaseAuth.getInstance().currentUser}")
                Destination.HOME_SCREEN
            } else
                Destination.LOGIN_SCREEN
        ) {
            composable(Destination.AUTHEN_SCREEN) {
                AuthenView(onLogin = { action.login }, onRegister = { action.register })
            }

            composable(Destination.LOGIN_SCREEN) {
                LoginView(onHome = { action.home() }, onNavigateBack = { action.navigateBack })
            }

            composable(Destination.REGISTER_SCREEN) {
                RegisterView(onLogin = { action.login() }, onNavigateBack = { action.navigateBack })
            }

            composable(Destination.HOME_SCREEN) {
                HomeView(
                    onLogout = { action.navigateBack() },
                    onChat = { receiverId -> action.chat(receiverId) })
            }

            composable(
                route = Destination.CHAT_SCREEN,
                arguments = listOf(navArgument("chatId") { type = NavType.StringType })
            ) { backStackEntry ->
                val chatId = backStackEntry.arguments?.getString("chatId")

                if (chatId != null) {
                    ChatView(chatId = chatId)
                }

            }
        }
    }


}