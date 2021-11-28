package com.kaleichyk.controllers

import com.kaleichyk.interactors.AuthInteractor
import com.kaleichyk.models.Token
import com.kaleichyk.models.UserAuth
import com.kaleichyk.responce.Response
import com.kaleichyk.responce.ok
import com.kaleichyk.responce.successful.LoginSuccessfulResponse

interface AuthController {

    fun login(user: UserAuth): Response

    fun refresh(refreshToken: Token): Response
}

class AuthControllerImpl(private val interactor: AuthInteractor) : AuthController {

    override fun login(user: UserAuth): Response = Response.ok(
        interactor.login(user).let { LoginSuccessfulResponse(it.token, it.refreshToken) }
    )

    override fun refresh(refreshToken: Token) = Response.ok(
        LoginSuccessfulResponse(interactor.refreshToken(refreshToken), refreshToken)
    )
}