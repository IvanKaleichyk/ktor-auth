package com.kaleichyk.utils.extensions

import com.auth0.jwt.algorithms.Algorithm
import io.ktor.application.Application
import io.ktor.config.ApplicationConfig

private const val SECRET_PATH = "jwt.secret"
private const val ISSUER_PATH = "jwt.issuer"
private const val REALM_PATH = "jwt.realm"
private const val SUBJECT_PATH = "jwt.subject"
private const val EXPIRATION_TIME_PATH = "jwt.expiration_time"

val Application.algorithm get() = environment.config.algorithm

val Application.issuer get() = environment.config.issuer

val Application.subject get() = environment.config.subject

val Application.realm get() = environment.config.realm

val Application.tokenValidTime get() = environment.config.tokenValidTime

val ApplicationConfig.algorithm: Algorithm
    get() = Algorithm.HMAC512(stringProperty(SECRET_PATH))

val ApplicationConfig.issuer get() = stringProperty(ISSUER_PATH)

val ApplicationConfig.realm get() = stringProperty(REALM_PATH)

val ApplicationConfig.subject get() = stringProperty(SUBJECT_PATH)

val ApplicationConfig.tokenValidTime get() = longProperty(EXPIRATION_TIME_PATH)

fun ApplicationConfig.stringProperty(path: String) = property(path).getString()

fun ApplicationConfig.longProperty(path: String) = property(path).getString().toLong()