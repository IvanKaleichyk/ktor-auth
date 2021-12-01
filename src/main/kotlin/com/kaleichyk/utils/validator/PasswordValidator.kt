package com.kaleichyk.utils.validator

import java.util.regex.Pattern

interface PasswordValidator : Validator<String> {

    fun arePasswordSame(password: String, repeatingPassword: String): ValidatorResponse
}

class PasswordValidatorImpl : PasswordValidator {

    companion object {
        const val MIN_LENGTH = 6
        const val MAX_LENGTH = 20
        const val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$"
    }

    override fun arePasswordSame(password: String, repeatingPassword: String) =
        when (password) {
            repeatingPassword -> ValidatorResponse.Valid
            else -> throw ValidateException("Password are not the same")
        }

    override fun validate(value: String): ValidatorResponse {
        val pattern = Pattern.compile(PASSWORD_PATTERN)
        val matcher = pattern.matcher(value)
        return if (matcher.matches()) ValidatorResponse.Valid
        else throw ValidateException("Invalid password")
    }
}

