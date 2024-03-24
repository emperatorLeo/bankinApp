package com.example.bankinapp.usecase.signUp

import com.example.bankinapp.domain.Repository
import com.example.bankinapp.data.db.entities.UserDataEntity
import javax.inject.Inject

class SignUpUseCaseImp @Inject constructor(private val repository: Repository) : SignUpUseCase {
    override operator fun invoke(email: String, userData: UserDataEntity) =
        repository.signUp(email, userData)
}
