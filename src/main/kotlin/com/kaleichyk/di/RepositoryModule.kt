package com.kaleichyk.di

import com.auth0.jwt.algorithms.Algorithm
import com.kaleichyk.repository.TokenRepository
import com.kaleichyk.repository.TokenRepositoryImpl
import com.kaleichyk.repository.UserRepository
import com.kaleichyk.repository.UserRepositoryImpl
import java.util.*
import org.koin.dsl.module

fun repositoryModule(
    algorithm: Algorithm,
    subject: String,
    issuer: String,
    tokenExpressAt: Date
) = module {
    single<TokenRepository> {
        TokenRepositoryImpl(
            algorithm,
            subject,
            issuer,
            tokenExpressAt,
            get()
        )
    }

    single<UserRepository> { UserRepositoryImpl() }
}