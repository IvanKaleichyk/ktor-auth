package com.kaleichyk.utils.validator

interface Validator<T> {

    fun validate(value: T): ValidatorResponse
}

sealed interface ValidatorResponse {

    object Valid : ValidatorResponse
    class Error(val message: String) : ValidatorResponse
}