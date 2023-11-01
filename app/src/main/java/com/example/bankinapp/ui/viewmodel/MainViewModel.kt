package com.example.bankinapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bankinapp.data.db.PASSWORD
import com.example.bankinapp.ui.states.LoginStates
import com.example.bankinapp.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {

    private val _uiStates = MutableLiveData<LoginStates>()
    val uiStates: LiveData<LoginStates> = _uiStates

    fun login(email: String, password: String) {
        _uiStates.value = LoginStates.Loading
        loginUseCase(email, password)
            .addOnSuccessListener {
                if (it.data == null || it.get(PASSWORD) != password){
                    _uiStates.value = LoginStates.WrongCredentials
                }else {
                    _uiStates.value = LoginStates.Success
                }
            }
            .addOnFailureListener {
                _uiStates.value = LoginStates.Failure
            }
    }
}
