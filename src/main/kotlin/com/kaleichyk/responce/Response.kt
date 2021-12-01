@file:Suppress("FunctionName")

package com.kaleichyk.responce

import io.ktor.http.HttpStatusCode

sealed class Response<T>(val code: HttpStatusCode, val body: T) {

    class Ok<T>(body: T) : Response<T>(HttpStatusCode.OK, body)
    class Created : Response<Boolean>(HttpStatusCode.Created, true)
    class Unauthorized(message: String) : Response<String>(HttpStatusCode.Unauthorized, message)
    class NotFound(message: String) : Response<String>(HttpStatusCode.NotFound, message)
}
