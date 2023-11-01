package com.example.bankinapp.data

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot

interface Repository {
    fun login(email: String, password: String): Task<DocumentSnapshot>
}
