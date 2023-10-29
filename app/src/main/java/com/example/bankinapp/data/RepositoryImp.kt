package com.example.bankinapp.data

import com.example.bankinapp.data.db.DataBaseSource
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot

class RepositoryImp(private val dataBaseSource: DataBaseSource) : Repository {
    override fun login(email: String, password: String): Task<QuerySnapshot> {
        return dataBaseSource.login(email = email, password = password)
    }
}
