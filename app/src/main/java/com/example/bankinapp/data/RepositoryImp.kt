package com.example.bankinapp.data

import com.example.bankinapp.data.db.entities.UserDataDTO
import com.example.bankinapp.domain.DataBaseSource
import com.example.bankinapp.domain.Repository
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot

class RepositoryImp(private val dataBaseSource: DataBaseSource) : Repository {
    override fun login(email: String, password: String): Task<DocumentSnapshot> {
        return dataBaseSource.login(email = email, password = password)
    }

    override fun signUp(email: String, userData: UserDataDTO) =
        dataBaseSource.signUp(email, userData)

    override fun uploadPhoto(photoName: String, byteArray: ByteArray) =
        dataBaseSource.uploadPhoto(photoName, byteArray)
}
