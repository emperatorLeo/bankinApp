package com.example.bankinapp.ui.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bankinapp.data.db.IMAGE_URL
import com.example.bankinapp.data.db.LASTNAME
import com.example.bankinapp.data.db.MOVEMENTS
import com.example.bankinapp.data.db.NAME
import com.example.bankinapp.data.db.PASSWORD
import com.example.bankinapp.data.db.entities.Movements
import com.example.bankinapp.data.db.entities.UserDataDTO
import com.example.bankinapp.data.db.entities.fromHashMapToMovements
import com.example.bankinapp.model.UserInformation
import com.example.bankinapp.ui.states.LoginUiStates
import com.example.bankinapp.ui.states.PhotoStates
import com.example.bankinapp.ui.states.SignUpStates
import com.example.bankinapp.usecase.login.LoginUseCase
import com.example.bankinapp.usecase.photo.UploadPhotoUseCase
import com.example.bankinapp.usecase.signUp.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val signUpUseCase: SignUpUseCase,
    private val uploadPhotoUseCase: UploadPhotoUseCase
) : ViewModel() {

    private val _loginUiStates = MutableStateFlow<LoginUiStates>(LoginUiStates.Idle)
    val loginUiStates: StateFlow<LoginUiStates> = _loginUiStates.asStateFlow()
    private val _signUpState = MutableStateFlow<SignUpStates>(SignUpStates.Idle)
    val signUpStates: StateFlow<SignUpStates> = _signUpState.asStateFlow()

    private val _user = MutableLiveData<Pair<String, UserDataDTO>>()
    private val _movementDetail = MutableLiveData<ArrayList<Movements>>(arrayListOf())
    lateinit var selectedMovement: Movements

    private val _photoState = MutableStateFlow<PhotoStates>(PhotoStates.Idle)
    val photoState: StateFlow<PhotoStates> = _photoState.asStateFlow()

    private val _photoTaken = MutableLiveData<Bitmap?>()
    val photoTaken: LiveData<Bitmap?> = _photoTaken

    private lateinit var userInformation: UserInformation

    @Suppress("UNCHECKED_CAST")
    fun login(email: String, password: String) {
        _loginUiStates.value = LoginUiStates.Loading
        loginUseCase(email, password)
            .addOnSuccessListener {
                if (it.data == null || it.get(PASSWORD) != password) {
                    _loginUiStates.value = LoginUiStates.WrongCredentials
                } else {
                    _loginUiStates.value = LoginUiStates.Success
                    val movementsRaw = it.get(MOVEMENTS) as ArrayList<HashMap<String, Any>>
                    _movementDetail.value = fromHashMapToMovements(movementsRaw)
                    _user.value =
                        Pair(
                            email,
                            UserDataDTO(
                                name = it.get(NAME) as String,
                                lastName = it.get(
                                    LASTNAME
                                ) as String,
                                password = it.get(PASSWORD) as String,
                                imageUrl = it.get(IMAGE_URL) as String,
                                movements = arrayListOf()
                            )
                        )
                }
            }
            .addOnFailureListener {
                _loginUiStates.value = LoginUiStates.Failure
            }
    }

    fun setUserInformation(userInformation: UserInformation) {
        _photoTaken.value = null
        _photoState.value = PhotoStates.Idle
        _signUpState.value = SignUpStates.Idle
        this.userInformation = userInformation
    }

    fun getUserData() = _user.value!!
    fun getMovementDetail() = _movementDetail.value

    fun selectMovement(index: Int) {
        selectedMovement = _movementDetail.value!![index]
    }

    fun setPhoto(photo: Bitmap){
        _photoTaken.value = photo
        _photoState.value = PhotoStates.Taken
    }

    fun signUp(photo: Bitmap) {
        _signUpState.value = SignUpStates.Loading
        uploadPhotoUseCase(email = userInformation.email, photo)
            .addOnSuccessListener {
                it.storage.downloadUrl.addOnSuccessListener { uri ->
                    savingUser(uri.toString())
                }
            }
            .addOnFailureListener {
                _signUpState.value = SignUpStates.Failure
            }
    }

    fun resetState(){
        _loginUiStates.value = LoginUiStates.Idle
    }

    private fun savingUser(imageUrl: String) {
        with(userInformation) {
            signUpUseCase(email, UserDataDTO(name, surname, password, imageUrl, arrayListOf()))
                .addOnSuccessListener {
                    _signUpState.value = SignUpStates.Success
                }
                .addOnFailureListener {
                    _signUpState.value = SignUpStates.Failure
                }
        }
    }
}
