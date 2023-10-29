package com.example.bankinapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bankinapp.ui.screens.LoginScreen
import com.example.bankinapp.ui.viewmodel.MainViewModel

@Composable
fun AppNavigation(viewModel: MainViewModel) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route){
            LoginScreen()
        }

    }
}