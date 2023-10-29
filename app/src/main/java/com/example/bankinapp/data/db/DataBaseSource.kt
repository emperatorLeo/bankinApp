package com.example.bankinapp.data.db

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot

interface DataBaseSource {
    fun login(email: String, password: String): Task<QuerySnapshot>
}
