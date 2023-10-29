package com.example.bankinapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.bankinapp.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {

    fun login(email: String, password: String) {
        val response = loginUseCase(email, password)
        response.addOnSuccessListener {
            Log.d("Leo", "ViewModel on Success")
        }.addOnFailureListener {
            Log.d("Leo", "ViewModel on Failure")
        }
    }
}
