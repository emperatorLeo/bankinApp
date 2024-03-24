package com.example.bankinapp.usecase.signUp

import com.example.bankinapp.domain.Repository
import com.example.bankinapp.data.db.entities.UserDataDTO
import javax.inject.Inject

class SignUpUseCaseImp @Inject constructor(private val repository: Repository) : SignUpUseCase {
    override operator fun invoke(email: String, userData: UserDataDTO) =
        repository.signUp(email, userData)
}
