package com.example.bankinapp.util

object EmailHelper {

        @Suppress("MaxLineLength")
        private const val VALID_EMAIL_REGEX =
            "[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])+"

        fun isEmailValid(email: String) =
            if (email.isNotEmpty()) email.lowercase().matches(Regex(VALID_EMAIL_REGEX)) else false
}