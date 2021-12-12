package com.kaleichyk.repository

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTCreator
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import com.auth0.jwt.interfaces.DecodedJWT
import com.kaleichyk.JwtVerifierService
import com.kaleichyk.exceptions.TokenException
import com.kaleichyk.models.Token
import com.kaleichyk.utils.extensions.ROLES_CLAIM
import com.kaleichyk.utils.extensions.getLogin
import com.kaleichyk.utils.extensions.getPassword
import com.kaleichyk.utils.extensions.getRoles
import java.util.*

interface TokenRepository {

    fun generateToken(name: String, password: String, roles: List<String>): Token

    fun generateRefreshToken(name: String, password: String, roles: List<String>): Token

    fun isTokenValid(token: String): Boolean

    fun refreshToken(refreshToken: Token): Token
}

class TokenRepositoryImpl(
    private val algorithm: Algorithm,
    private val subject: String,
    private val issuer: String,
    private val tokenExpiresAt: Date,
    private val verifierService: JwtVerifierService
) : TokenRepository {

    companion object {

        const val NAME_CLAIM = "NAME_CLAIM"
        const val PASSWORD_CLAIM = "PASSWORD_CLAIM"
    }

    override fun generateToken(name: String, password: String, roles: List<String>): Token = buildToken {
        withClaim(NAME_CLAIM, name)
        withClaim(PASSWORD_CLAIM, password)
        withClaim(ROLES_CLAIM, roles)
        withExpiresAt(tokenExpiresAt)
    }

    override fun generateRefreshToken(name: String, password: String, roles: List<String>): Token = buildToken {
        withClaim(NAME_CLAIM, name)
        withClaim(PASSWORD_CLAIM, password)
        withClaim(ROLES_CLAIM, roles)
    }

    override fun isTokenValid(token: String) =
        try {
            verifierService.verifier.verify(token) != null
        } catch (exception: JWTVerificationException) {
            false
        }

    private fun tryGetDecodedJWT(token: String): DecodedJWT? =
        try {
            verifierService.verifier.verify(token)
        } catch (exception: JWTVerificationException) {
            null
        }

    override fun refreshToken(refreshToken: Token): Token {
        tryGetDecodedJWT(refreshToken)?.let {
            val login = it.getLogin()
            val password = it.getPassword()
            val roles = it.getRoles()
            return generateToken(login, password, roles)
        } ?: throw TokenException.NotValid
    }


    private fun buildToken(block: JWTCreator.Builder.() -> JWTCreator.Builder) = JWT
        .create()
        .withSubject(subject)
        .withIssuer(issuer)
        .withIssuedAt(Date())
        .block()
        .sign(algorithm)

}