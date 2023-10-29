package com.example.bankinapp.data

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot

interface Repository {
    fun login(email: String, password: String): Task<QuerySnapshot>
}
