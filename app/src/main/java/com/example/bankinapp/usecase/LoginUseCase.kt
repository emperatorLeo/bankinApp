package com.example.bankinapp.usecase

import com.example.bankinapp.data.Repository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: Repository) {
    operator fun invoke(email: String, password: String) = repository.login(email, password)
}
