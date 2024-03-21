package com.example.bankinapp.ui.navigation

import androidx.camera.view.LifecycleCameraController
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bankinapp.ui.screens.HomeScreen
import com.example.bankinapp.ui.screens.LoginScreen
import com.example.bankinapp.ui.screens.SignUpScreen
import com.example.bankinapp.ui.screens.TransactionDetailScreen
import com.example.bankinapp.ui.viewmodel.MainViewModel

@Composable
fun AppNavigation(viewModel: MainViewModel = viewModel(), cameraController: LifecycleCameraController) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            LoginScreen(viewModel = viewModel, navController)
        }

        composable(Screen.SignUp.route) {
            SignUpScreen(viewModel = viewModel, cameraController, navController)
        }

        composable(Screen.Home.route) {
            HomeScreen(viewModel = viewModel, navController = navController)
        }

        composable(Screen.TransactionDetail.route) {
            TransactionDetailScreen(viewModel = viewModel)
        }
    }
}
