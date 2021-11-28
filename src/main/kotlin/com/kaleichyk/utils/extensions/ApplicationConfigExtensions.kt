package com.kaleichyk.utils.extensions

import com.auth0.jwt.algorithms.Algorithm
import io.ktor.config.*

private const val SECRET_PATH = "jwt.secret"
private const val ISSUER_PATH = "jwt.issuer"
private const val REALM_PATH = "jwt.realm"
private const val EXPIRATION_TIME_PATH = "jwt.expiration_time"

val ApplicationConfig.algorithm: Algorithm
    get() = Algorithm.HMAC512(stringProperty(SECRET_PATH))

val ApplicationConfig.issuer get() = stringProperty(ISSUER_PATH)

val ApplicationConfig.realm get() = stringProperty(REALM_PATH)

val ApplicationConfig.tokenValidTime get() = longProperty(EXPIRATION_TIME_PATH)

fun ApplicationConfig.stringProperty(path: String) = property(path).getString()

fun ApplicationConfig.longProperty(path: String) = property(path).getString().toLong()