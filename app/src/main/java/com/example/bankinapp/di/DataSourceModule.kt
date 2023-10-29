package com.example.bankinapp.di

import com.example.bankinapp.data.Repository
import com.example.bankinapp.data.RepositoryImp
import com.example.bankinapp.data.db.DataBaseSource
import com.example.bankinapp.data.db.DataBaseSourceImp
import com.google.firebase.firestore.FirebaseFirestore
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
    fun provideDataBaseSource(firebaseFireStore: FirebaseFirestore): DataBaseSource {
        return DataBaseSourceImp(firebaseFireStore)
    }

    @Singleton
    @Provides
    fun provideRepository(dataBaseSource: DataBaseSource): Repository {
        return RepositoryImp(dataBaseSource)
    }
}
