package com.example.bankinapp.ui.viewmodel

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bankinapp.data.db.LASTNAME
import com.example.bankinapp.data.db.MOVEMENTS
import com.example.bankinapp.data.db.NAME
import com.example.bankinapp.data.db.PASSWORD
import com.example.bankinapp.data.db.entities.Movements
import com.example.bankinapp.data.db.entities.UserDataEntity
import com.example.bankinapp.data.db.entities.fromHashMapToMovements
import com.example.bankinapp.model.UserInformation
import com.example.bankinapp.ui.states.LoginUiStates
import com.example.bankinapp.ui.states.SignUpStates
import com.example.bankinapp.usecase.LoginUseCase
import com.example.bankinapp.usecase.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    private val _loginUiStates = MutableStateFlow<LoginUiStates>(LoginUiStates.Idle)
    val loginUiStates: StateFlow<LoginUiStates> = _loginUiStates.asStateFlow()
    private val _signUpState = MutableStateFlow<SignUpStates>(SignUpStates.Idle)
    val signUpStates: StateFlow<SignUpStates> = _signUpState.asStateFlow()

    private val _user = MutableLiveData<Pair<String, UserDataEntity>>()
    private val _movementDetail = MutableLiveData<ArrayList<Movements>>(arrayListOf())
    lateinit var selectedMovement: Movements

    private val _photoTaken = MutableLiveData<Bitmap>()
    val photoTaken: LiveData<Bitmap> = _photoTaken

    @Suppress("UNCHECKED_CAST")
    fun login(email: String, password: String) {
        _loginUiStates.value = LoginUiStates.Loading
        loginUseCase(email, password)
            .addOnSuccessListener {
                if (it.data == null || it.get(PASSWORD) != password) {
                    Log.d("Error in FireStore", "viewModel wrongCredentials")
                    _loginUiStates.value = LoginUiStates.WrongCredentials
                } else {
                    _loginUiStates.value = LoginUiStates.Success
                    val movementsRaw = it.get(MOVEMENTS) as ArrayList<HashMap<String, Any>>
                    _movementDetail.value = fromHashMapToMovements(movementsRaw)

                    _user.value =
                        Pair(
                            email,
                            UserDataEntity(
                                name = it.get(NAME) as String,
                                lastName = it.get(
                                    LASTNAME
                                ) as String,
                                password = it.get(PASSWORD) as String,
                                movements = arrayListOf()
                            )
                        )
                }
            }
            .addOnFailureListener {
                _loginUiStates.value = LoginUiStates.Failure
            }
    }

    fun signUp(userInformation: UserInformation) {
        with(userInformation) {
            if (email.isEmpty() || password.isEmpty() || name.isEmpty() || surname.isEmpty() || _photoTaken.value == null) {
                _signUpState.value = SignUpStates.EmptyFields
            } else {
                _signUpState.value = SignUpStates.Loading

                signUpUseCase(email, UserDataEntity(name, surname, password, arrayListOf()))
                    .addOnSuccessListener {
                        _signUpState.value = SignUpStates.Success
                    }
                    .addOnFailureListener {
                        _signUpState.value = SignUpStates.Failure
                    }
            }
        }
    }

    fun getUserData() = _user.value
    fun getMovementDetail() = _movementDetail.value

    fun selectMovement(index: Int) {
        selectedMovement = _movementDetail.value!![index]
    }

    fun setPhoto(photo: Bitmap) {
        _photoTaken.value = photo
    }
}
