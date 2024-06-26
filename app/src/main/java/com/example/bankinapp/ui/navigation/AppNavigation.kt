package com.example.bankinapp.ui.navigation

import android.annotation.SuppressLint
import androidx.camera.view.LifecycleCameraController
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bankinapp.ui.screens.HomeScreen
import com.example.bankinapp.ui.screens.LoginScreen
import com.example.bankinapp.ui.screens.PhotoScreen
import com.example.bankinapp.ui.screens.SignUpScreen
import com.example.bankinapp.ui.screens.SuccessScreen
import com.example.bankinapp.ui.screens.TransactionDetailScreen
import com.example.bankinapp.ui.viewmodel.MainViewModel

@SuppressLint("RestrictedApi")
@Composable
fun AppNavigation(
    viewModel: MainViewModel = viewModel(),
    cameraController: LifecycleCameraController
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {

        composable(Screen.Login.route) {
            LoginScreen(viewModel = viewModel, navController)
        }

        composable(Screen.SignUp.route) {
            SignUpScreen(
                viewModel = viewModel,
                navController = navController
            )
        }

        composable(Screen.TakePhoto.route) {
            PhotoScreen(
                cameraController = cameraController,
                viewModel = viewModel,
                navController = navController
            )
        }

        composable(Screen.Success.route) {
            SuccessScreen(navController = navController)
        }

        composable(Screen.Home.route) {
            HomeScreen(viewModel = viewModel, navController = navController)
        }

        composable(Screen.TransactionDetail.route) {
            TransactionDetailScreen(viewModel = viewModel, navController = navController)
        }
    }
}
