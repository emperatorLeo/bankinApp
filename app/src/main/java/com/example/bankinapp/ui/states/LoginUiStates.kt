package com.example.bankinapp.ui.states

sealed class LoginUiStates {
    object Loading : LoginUiStates()

    object Success : LoginUiStates()

    object WrongCredentials : LoginUiStates()

    object Failure : LoginUiStates()

    object Idle: LoginUiStates()
}
