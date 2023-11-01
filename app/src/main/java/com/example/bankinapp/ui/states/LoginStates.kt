package com.example.bankinapp.ui.states

sealed class LoginStates {
    object Loading : LoginStates()

    object Success : LoginStates()

    object WrongCredentials : LoginStates()

    object Failure : LoginStates()
}