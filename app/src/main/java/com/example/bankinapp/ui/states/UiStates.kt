package com.example.bankinapp.ui.states

sealed class UiStates {
    object Loading : UiStates()

    object Success : UiStates()

    object WrongCredentials : UiStates()

    object Failure : UiStates()

    object EmptyFields : UiStates()
}
