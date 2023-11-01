package com.example.bankinapp.di

import com.example.bankinapp.data.Repository
import com.example.bankinapp.usecase.BaseUseCase
import com.example.bankinapp.usecase.LoginUseCase
import com.example.bankinapp.usecase.SignUpUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @com.example.bankinapp.di.LoginUseCase
    @Provides
    fun provideLoginUseCase(repository: Repository): BaseUseCase {
        return LoginUseCase(repository)
    }
    @com.example.bankinapp.di.SignUpUseCase
    @Provides
    fun provideSignUpUseCase(repository: Repository): BaseUseCase{
        return SignUpUseCase(repository)
    }
}