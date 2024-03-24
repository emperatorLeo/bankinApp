package com.example.bankinapp.usecase.login

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot

interface LoginUseCase {

    operator fun invoke(email: String, password: String): Task<DocumentSnapshot>
}