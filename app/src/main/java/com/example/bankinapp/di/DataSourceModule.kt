package com.example.bankinapp.di

import com.example.bankinapp.domain.Repository
import com.example.bankinapp.data.RepositoryImp
import com.example.bankinapp.domain.DataBaseSource
import com.example.bankinapp.data.db.DataBaseSourceImp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun provideFirebaseFireStore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Singleton
    @Provides
    fun provideDataBaseSource(
        firebaseFireStore: FirebaseFirestore,
        storageReference: StorageReference
    ): DataBaseSource {
        return DataBaseSourceImp(firebaseFireStore, storageReference)
    }

    @Singleton
    @Provides
    fun provideRepository(dataBaseSource: DataBaseSource): Repository {
        return RepositoryImp(dataBaseSource)
    }

    @Singleton
    @Provides
    fun provideStorage(): FirebaseStorage {
        return Firebase.storage
    }

    @Provides
    fun provideStorageReference(firebaseStorage: FirebaseStorage): StorageReference {
        return firebaseStorage.reference
    }
}
