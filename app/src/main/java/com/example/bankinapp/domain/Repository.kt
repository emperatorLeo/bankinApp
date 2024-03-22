package com.example.bankinapp.domain

import com.example.bankinapp.data.db.entities.UserDataEntity
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.storage.UploadTask

interface Repository {
    fun login(email: String, password: String): Task<DocumentSnapshot>

    fun signUp(email: String, userData: UserDataEntity): Task<Void>

    fun uploadPhoto(photoName: String, byteArray: ByteArray): UploadTask
}
