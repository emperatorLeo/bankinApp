package com.example.bankinapp.ui.navigation

sealed class Screen(val route: String) {

    object Login: Screen("Login")

    object Home: Screen("Home")
}