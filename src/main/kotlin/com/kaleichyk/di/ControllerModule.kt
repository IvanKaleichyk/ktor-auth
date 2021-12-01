package com.kaleichyk.di

import com.kaleichyk.controllers.AuthController
import com.kaleichyk.controllers.AuthControllerImpl
import org.koin.dsl.module

val controllerModule = module {
    single<AuthController> { AuthControllerImpl(get(), get()) }
}