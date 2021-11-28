package com.kaleichyk

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.JWTVerifier

interface JwtVerifierService {

    val verifier: JWTVerifier
}

class JwtVerifierServiceImpl(algorithm: Algorithm, issuer: String) : JwtVerifierService {

    override val verifier = JWT.require(algorithm).withIssuer(issuer).build()
}