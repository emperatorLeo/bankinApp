package com.example.bankinapp.ui.navigation

sealed class Screen(val route: String) {

    object Login : Screen("Login")

    object SignUp : Screen("SignUp")

    object Home : Screen("Home")

    object TransactionDetail : Screen("Transaction_Detail")
}
