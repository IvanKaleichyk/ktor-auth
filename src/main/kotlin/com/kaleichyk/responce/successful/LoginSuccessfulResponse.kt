package com.kaleichyk.responce.successful

import com.kaleichyk.models.Token
import com.kaleichyk.responce.ResponseBody

data class LoginSuccessfulResponse(
    val token: Token,
    val refreshToken: Token
): ResponseBody