package com.example.bankinapp.usecase.login

import com.example.bankinapp.domain.Repository
import javax.inject.Inject

class LoginUseCaseImp @Inject constructor(private val repository: Repository): LoginUseCase {
    override operator fun invoke(email: String, password: String) = repository.login(email, password)
}
