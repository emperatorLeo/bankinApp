package com.example.bankinapp.usecase.photo

import android.graphics.Bitmap
import com.google.firebase.storage.UploadTask

interface UploadPhotoUseCase {
    operator fun invoke(email: String, bitmap: Bitmap): UploadTask
}