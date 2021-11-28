package com.kaleichyk.exceptions

sealed class TokenException(override val message: String) : Exception(message) {
    object NotValid : TokenException("Token is not valid")
}