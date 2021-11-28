package com.kaleichyk.models

import kotlinx.serialization.Serializable

typealias Token = String

@Serializable
data class Tokens(val token: Token, val refreshToken: String)

@Serializable
data class TokenRequest(val token: Token)