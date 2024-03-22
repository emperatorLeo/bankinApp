package com.example.bankinapp.data.db

import com.example.bankinapp.data.db.entities.Movements
import com.example.bankinapp.data.db.entities.UserDataEntity
import com.example.bankinapp.domain.DataBaseSource
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import javax.inject.Inject

class DataBaseSourceImp @Inject constructor(
    private val db: FirebaseFirestore,
    private val storageRef: StorageReference
) : DataBaseSource {

    override fun login(email: String, password: String): Task<DocumentSnapshot> {
        return db.collection(COLLECTION).document(email)
            .get()
    }

    override fun signUp(email: String, userData: UserDataEntity) =
        db.collection(COLLECTION).document(email)
            .set(
                hashMapOf(
                    NAME to userData.name,
                    LASTNAME to userData.lastName,
                    PASSWORD to userData.password,
                    IMAGE_URL to userData.imageUrl,
                    MOVEMENTS to arrayListOf<Movements>()
                )
            )


    override fun uploadPhoto(photoName: String, byteArray: ByteArray): UploadTask {
        return storageRef.child(CHILD_REFERENCE).child(photoName).putBytes(byteArray)
    }
}
