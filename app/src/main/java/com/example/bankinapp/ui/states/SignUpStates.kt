package com.example.bankinapp.ui.states

sealed class SignUpStates {

    object Idle : SignUpStates()
    object EmptyFields : SignUpStates()

    object Loading : SignUpStates()

    object Success : SignUpStates()

    object Failure : SignUpStates()
}