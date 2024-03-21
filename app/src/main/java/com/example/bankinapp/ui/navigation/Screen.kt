package com.example.bankinapp.ui.navigation

sealed class Screen(val route: String) {

    object Login : Screen("login_screen")

    object SignUp : Screen("sign_up_screen")

    object TakePhoto : Screen("take_photo_screen")

    object Home : Screen("home_screen")

    object TransactionDetail : Screen("transaction_detail_screen")
}
