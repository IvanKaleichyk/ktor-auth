package com.kaleichyk.exceptions

sealed class PayloadException(override val message: String) : Exception(message) {

    object CannotGetLogin : PayloadException("Cannot get login")
    object CannotGetPassword : PayloadException("Cannot get password")
}