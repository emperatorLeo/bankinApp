package com.example.bankinapp.ui.states

data class InputTextState(
    var isEmailOk: Boolean,
    var isPasswordOk: Boolean,
    var isNameOk: Boolean,
    var isSurnameOk: Boolean
){
    fun areAllOk() = isEmailOk && isPasswordOk && isNameOk && isSurnameOk
    companion object{
        val idle = InputTextState(
            isEmailOk = false,
            isPasswordOk = false,
            isNameOk = false,
            isSurnameOk = false
        )
    }
}