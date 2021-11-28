package com.kaleichyk.interactors

import com.kaleichyk.models.Token
import com.kaleichyk.models.Tokens
import com.kaleichyk.models.UserAuth
import com.kaleichyk.repository.TokenRepository
import com.kaleichyk.repository.UserRepository

interface AuthInteractor {

    fun isTokenValid(token: Token): Boolean

    fun login(user: UserAuth): Tokens

    fun refreshToken(refreshToken: Token): Token
}

class AuthInteractorImpl(
    private val repository: TokenRepository,
    private val userRepository: UserRepository
) : AuthInteractor {

    override fun isTokenValid(token: Token) = repository.isTokenValid(token)

    override fun login(user: UserAuth) = user.run {
        userRepository.addUser(user.toUser())
        Tokens(
            repository.generateToken(name, password),
            repository.generateRefreshToken(name, password)
        )
    }

    override fun refreshToken(refreshToken: Token) = repository.refreshToken(refreshToken)
}
