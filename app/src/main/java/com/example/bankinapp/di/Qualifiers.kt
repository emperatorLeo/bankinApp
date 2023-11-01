package com.example.bankinapp.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LoginUseCase

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SignUpUseCase