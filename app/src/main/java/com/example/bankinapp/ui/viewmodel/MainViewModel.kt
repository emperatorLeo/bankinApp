package com.example.bankinapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bankinapp.data.db.PASSWORD
import com.example.bankinapp.data.db.entities.UserDataEntity
import com.example.bankinapp.ui.states.UiStates
import com.example.bankinapp.usecase.LoginUseCase
import com.example.bankinapp.usecase.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    private val _uiStates = MutableLiveData<UiStates>()
    val uiStates: LiveData<UiStates> = _uiStates

    fun login(email: String, password: String) {
        _uiStates.value = UiStates.Loading
        loginUseCase(email, password)
            .addOnSuccessListener {
                if (it.data == null || it.get(PASSWORD) != password) {
                    _uiStates.value = UiStates.WrongCredentials
                } else {
                    _uiStates.value = UiStates.Success
                }
            }
            .addOnFailureListener {
                _uiStates.value = UiStates.Failure
            }
    }

    fun signUp(email: String, userData: UserDataEntity) {
        if (email.isEmpty() || userData.password.isEmpty() || userData.name.isEmpty() || userData.lastName.isEmpty()) {
            _uiStates.value = UiStates.EmptyFields
        } else {
            _uiStates.value = UiStates.Loading
            signUpUseCase(email, userData)
                .addOnSuccessListener {
                    _uiStates.value = UiStates.Success
                }
                .addOnFailureListener {
                    _uiStates.value = UiStates.Failure
                }
        }
    }
}
