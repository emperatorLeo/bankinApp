package com.example.bankinapp.model

data class UserInformation(
    val email: String,
    val password: String,
    val name: String,
    val surname: String
) {
    companion object {
        val userEmpty = UserInformation("", "", "", "")
    }
}
