package com.kaleichyk.di

import com.kaleichyk.interactors.AuthInteractor
import com.kaleichyk.interactors.AuthInteractorImpl
import com.kaleichyk.interactors.UserInteractor
import com.kaleichyk.interactors.UserInteractorImpl
import org.koin.dsl.module

val interactorModule = module {

    single<AuthInteractor> { AuthInteractorImpl(get(), get()) }

    single<UserInteractor> { UserInteractorImpl(get()) }
}