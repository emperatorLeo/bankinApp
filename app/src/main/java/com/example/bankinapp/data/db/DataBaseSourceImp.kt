package com.example.bankinapp.data.db

import com.example.bankinapp.data.db.entities.Movements
import com.example.bankinapp.data.db.entities.UserDataEntity
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class DataBaseSourceImp @Inject constructor(private val db: FirebaseFirestore) : DataBaseSource {

    override fun login(email: String, password: String): Task<DocumentSnapshot> {
        return db.collection(COLLECTION).document(email)
            .get()
    }

    override fun signUp(email: String, userData: UserDataEntity) =
        db.collection(COLLECTION).document(email)
        .set(hashMapOf(
            NAME to userData.name,
            LASTNAME to userData.lastName,
            PASSWORD to userData.password,
            MOVEMENTS to arrayListOf<Movements>()
        ))

}
