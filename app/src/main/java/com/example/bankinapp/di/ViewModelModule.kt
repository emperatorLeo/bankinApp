package com.example.bankinapp.di

import com.example.bankinapp.domain.Repository
import com.example.bankinapp.ui.viewmodel.MainViewModel
import com.example.bankinapp.usecase.login.LoginUseCase
import com.example.bankinapp.usecase.login.LoginUseCaseImp
import com.example.bankinapp.usecase.photo.UploadPhotoUseCase
import com.example.bankinapp.usecase.photo.UploadPhotoUseCaseImp
import com.example.bankinapp.usecase.signUp.SignUpUseCase
import com.example.bankinapp.usecase.signUp.SignUpUseCaseImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {
    @Provides
    fun provideViewModel(
        loginUseCase: LoginUseCase,
        signUpUseCase: SignUpUseCase,
        uploadPhotoUseCase: UploadPhotoUseCase
    ): MainViewModel {
        return MainViewModel(loginUseCase, signUpUseCase, uploadPhotoUseCase)
    }

    @Provides
    fun providesLoginUseCase(repository: Repository): LoginUseCase {
        return LoginUseCaseImp(repository)
    }

    @Provides
    fun providesSignUpUseCase(repository: Repository): SignUpUseCase {
        return SignUpUseCaseImp(repository)
    }

    @Provides
    fun providesUploadPhotoUseCase(repository: Repository): UploadPhotoUseCase {
        return UploadPhotoUseCaseImp(repository)
    }
}