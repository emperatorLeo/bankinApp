package com.example.bankinapp.ui.states

sealed class PhotoStates {
    object Taken : PhotoStates()

    object Idle : PhotoStates()
}