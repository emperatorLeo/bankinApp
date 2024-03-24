package com.example.bankinapp.usecase.signUp

import com.example.bankinapp.data.db.entities.UserDataEntity
import com.google.android.gms.tasks.Task

interface SignUpUseCase {
    operator fun invoke(email: String, userData: UserDataEntity): Task<Void>
}