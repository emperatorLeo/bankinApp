package com.example.bankinapp.ui.states

sealed class SignUpStates {
    object Idle : SignUpStates()
    object Loading : SignUpStates()
    object Success : SignUpStates()
    object Failure : SignUpStates()
}