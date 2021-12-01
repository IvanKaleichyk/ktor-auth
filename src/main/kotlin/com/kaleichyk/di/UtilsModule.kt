package com.kaleichyk.di

import com.kaleichyk.utils.validator.PasswordValidator
import com.kaleichyk.utils.validator.PasswordValidatorImpl
import org.koin.dsl.module

val utilsModule = module {
    factory<PasswordValidator> { PasswordValidatorImpl() }
}