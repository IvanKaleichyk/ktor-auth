package com.kaleichyk.di

import com.auth0.jwt.algorithms.Algorithm
import com.kaleichyk.JwtVerifierService
import com.kaleichyk.JwtVerifierServiceImpl
import org.koin.dsl.module

fun serviceModule(algorithm: Algorithm, issuer: String) = module {
    single<JwtVerifierService> { JwtVerifierServiceImpl(algorithm, issuer) }
}