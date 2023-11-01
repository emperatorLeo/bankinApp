package com.example.bankinapp.di

import com.example.bankinapp.data.Repository
import com.example.bankinapp.usecase.BaseUseCase
import com.example.bankinapp.usecase.LoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideDataBaseSource(repository: Repository): BaseUseCase {
        return LoginUseCase(repository)
    }
}