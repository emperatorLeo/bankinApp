package com.example.bankinapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.bankinapp.ui.theme.BankinAppTheme
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BankinAppTheme {
                val db = FirebaseFirestore.getInstance()
                db.collection("User")
                    .get()
                    .addOnSuccessListener { result ->
                        for (user in result) {
                            Log.d("Leo", "id: ${user.id} ${user.data["Movements"]}")
                        }
                    }
                    .addOnFailureListener { exeption ->
                        Log.d("Leo", "something went wrong ${exeption.message}")
                    }
            }
        }
    }
}
