package com.example.bankinapp.usecase.photo

import android.graphics.Bitmap
import com.example.bankinapp.domain.Repository
import com.google.firebase.storage.UploadTask
import java.io.ByteArrayOutputStream
import javax.inject.Inject

class UploadPhotoUseCaseImp @Inject constructor(private val repository: Repository): UploadPhotoUseCase {
    override operator fun invoke(email: String, bitmap: Bitmap): UploadTask {
        val photoName = "$email.jpg"
        val bitmapConverted = convertBitmap(bitmap)
        return repository.uploadPhoto(photoName, bitmapConverted)
    }

    private fun convertBitmap(bitmap: Bitmap): ByteArray {
        val byteArray = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArray)
        return byteArray.toByteArray()
    }
}