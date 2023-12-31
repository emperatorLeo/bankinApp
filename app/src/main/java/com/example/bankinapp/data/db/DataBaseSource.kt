package com.example.bankinapp.data.db

import com.example.bankinapp.data.db.entities.UserDataEntity
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot

interface DataBaseSource {
    fun login(email: String, password: String): Task<DocumentSnapshot>

    fun signUp(email: String, userData: UserDataEntity): Task<Void>
}
