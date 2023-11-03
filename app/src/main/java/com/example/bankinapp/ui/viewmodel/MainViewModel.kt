package com.example.bankinapp.ui.viewmodel

import android.graphics.Bitmap
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
    private val _user = MutableLiveData<Pair<String, UserDataEntity>>()
    private val _movementDetail = MutableLiveData<ArrayList<Movements>>(arrayListOf())
    lateinit var selectedMovement: Movements

    private val _photoTaken = MutableLiveData<Bitmap>()
    val photoTaken: LiveData<Bitmap> = _photoTaken

    @Suppress("UNCHECKED_CAST")
    fun login(email: String, password: String) {
        _uiStates.value = UiStates.Loading
        loginUseCase(email, password)
            .addOnSuccessListener {
                if (it.data == null || it.get(PASSWORD) != password) {
                    _uiStates.value = UiStates.WrongCredentials
                } else {
                    _uiStates.value = UiStates.Success
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
                _uiStates.value = UiStates.Failure
            }
    }

    fun signUp(email: String, userData: UserDataEntity) {
        if (email.isEmpty() || userData.password.isEmpty() || userData.name.isEmpty() || userData.lastName.isEmpty() || _photoTaken.value == null) {
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

    fun getUserData() = _user.value
    fun getMovementDetail() = _movementDetail.value

    fun selectMovement(index: Int) {
        selectedMovement = _movementDetail.value!![index]
    }

    fun setPhoto(photo: Bitmap) {
        _photoTaken.value = photo
    }
}
