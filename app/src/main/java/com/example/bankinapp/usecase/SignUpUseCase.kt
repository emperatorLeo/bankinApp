package com.example.bankinapp.usecase

import com.example.bankinapp.data.Repository
import com.example.bankinapp.data.db.entities.UserDataEntity
import javax.inject.Inject

class SignUpUseCase @Inject constructor(val repository: Repository) : BaseUseCase {
    operator fun invoke(email: String, userData: UserDataEntity) =
        repository.signUp(email, userData)
}