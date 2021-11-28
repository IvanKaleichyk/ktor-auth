package com.kaleichyk.utils.extensions

import com.auth0.jwt.interfaces.Payload
import com.kaleichyk.exceptions.PayloadException
import com.kaleichyk.repository.TokenRepositoryImpl.Companion.NAME_CLAIM
import com.kaleichyk.repository.TokenRepositoryImpl.Companion.PASSWORD_CLAIM

fun Payload.getLogin() = getClaim(NAME_CLAIM)?.asString() ?: throw PayloadException.CannotGetLogin

fun Payload.getPassword() = getClaim(PASSWORD_CLAIM)?.asString() ?: throw PayloadException.CannotGetPassword