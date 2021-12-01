package com.kaleichyk.responce.error

import com.kaleichyk.responce.ResponseBody

sealed class ErrorResponseBody(open val errorMessage: String) : ResponseBody {

    class AuthorizationFailed(errorMessage: String = "AuthorizationFailed") : ErrorResponseBody(errorMessage)

    class InputInvalid(errorMessage: String = "InputInvalid") : ErrorResponseBody(errorMessage)

    class ResourceAlreadyExists(errorMessage: String = "Resource already exists") : ErrorResponseBody(errorMessage)

    class InternalServer(errorMessage: String = "Internal server error occurred") : ErrorResponseBody(errorMessage)

    class Unknown(errorMessage: String = "UnknownError") : ErrorResponseBody(errorMessage)
}