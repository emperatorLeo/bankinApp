package com.example.bankinapp.ui.states

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class InputTextState(
    var isEmailOk: Boolean,
    var isPasswordOk: Boolean,
    var isNameOk: Boolean,
    var isSurnameOk: Boolean
) : Parcelable {
    fun areAllOk() = isEmailOk && isPasswordOk && isNameOk && isSurnameOk

    companion object {
        val idle = InputTextState(
            isEmailOk = false,
            isPasswordOk = false,
            isNameOk = false,
            isSurnameOk = false
        )
    }
}