package com.kaleichyk.controllers

import com.kaleichyk.interactors.AuthInteractor
import com.kaleichyk.models.Token
import com.kaleichyk.models.Tokens
import com.kaleichyk.models.UserAuth
import com.kaleichyk.responce.Response
import com.kaleichyk.utils.validator.PasswordValidator

interface AuthController {

    fun login(user: UserAuth): Response<Tokens>

    fun refresh(refreshToken: Token): Response<Tokens>
}

class AuthControllerImpl(
    private val interactor: AuthInteractor,
    private val passwordValidator: PasswordValidator
) : AuthController {

    override fun login(user: UserAuth): Response<Tokens> {
        passwordValidator.run {
            arePasswordSame(user.password, user.repeatPassword)
            validate(user.password)
        }
        return Response.Ok(interactor.login(user).let {
            Tokens(it.token, it.refreshToken)
        })
    }

    override fun refresh(refreshToken: Token): Response<Tokens> = Response.Ok(
        Tokens(interactor.refreshToken(refreshToken), refreshToken)
    )
}