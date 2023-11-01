package com.example.bankinapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.bankinapp.data.db.NAME
import com.example.bankinapp.data.db.PASSWORD
import com.example.bankinapp.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {

    fun login(email: String, password: String) {
        loginUseCase(email, password)
            .addOnSuccessListener {
                if (it.data == null || it.get(PASSWORD) != password){
                    Log.d("Leo","ese coño no existe")
                }else {
                    Log.d("Leo","Bienvenido: ${it.get(NAME)}")
                }
            }
            .addOnFailureListener {

            }
    }
}
