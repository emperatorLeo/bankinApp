package com.example.bankinapp.data.db

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class DataBaseSourceImp @Inject constructor(private val db: FirebaseFirestore) : DataBaseSource {

    override fun login(email: String, password: String): Task<DocumentSnapshot> {
          return  db.collection("User").document(email)
            .get()
    }
}
