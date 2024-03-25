package com.example.bankinapp.domain

data class SourceCallback<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): SourceCallback<T> {
            return SourceCallback(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T? = null): SourceCallback<T> {
            return SourceCallback(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T? = null): SourceCallback<T> {
            return SourceCallback(Status.LOADING, data, null)
        }
    }
}