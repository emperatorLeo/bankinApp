package com.example.bankinapp.data.db

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import javax.inject.Inject

class DataBaseSourceImp @Inject constructor(private val db: FirebaseFirestore) : DataBaseSource {
    override fun login(email: String, password: String): Task<QuerySnapshot> {
        Log.d("Leo", "Launching fireStore Query with $email ; $password...")
        return db.collection("User")
            .get()
            .addOnSuccessListener { result ->
                for (user in result) {
                    Log.d("Leo", " dataSource id: ${user.id} ${user.data["Movements"]}")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("Leo", "dataSource something went wrong ${exception.message}")
            }
    }
}
