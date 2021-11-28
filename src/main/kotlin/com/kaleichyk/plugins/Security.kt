package com.kaleichyk.plugins

import com.kaleichyk.JwtVerifierServiceImpl
import com.kaleichyk.controllers.AuthControllerImpl
import com.kaleichyk.interactors.AuthInteractorImpl
import com.kaleichyk.models.TokenRequest
import com.kaleichyk.models.UserAuth
import com.kaleichyk.repository.TokenRepositoryImpl
import com.kaleichyk.repository.UserRepositoryImpl
import com.kaleichyk.utils.extensions.algorithm
import com.kaleichyk.utils.extensions.issuer
import com.kaleichyk.utils.extensions.realm
import com.kaleichyk.utils.extensions.subject
import com.kaleichyk.utils.extensions.tokenValidTime
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.auth.jwt.JWTCredential
import io.ktor.auth.jwt.JWTPrincipal
import io.ktor.auth.jwt.jwt
import io.ktor.response.respond
import io.ktor.routing.post
import io.ktor.routing.route
import io.ktor.routing.routing
import java.util.*

fun Application.configureSecurity() {
    val tokenExpiresAt = Date(System.currentTimeMillis() + environment.config.tokenValidTime)

    val verifier = JwtVerifierServiceImpl(algorithm, issuer)
    val authInteractor =
        AuthInteractorImpl(
            TokenRepositoryImpl(algorithm, subject, issuer, tokenExpiresAt, JwtVerifierServiceImpl(algorithm, issuer)),
            UserRepositoryImpl()
        )

    val authController = AuthControllerImpl(authInteractor)

    install(Authentication) {
        jwt {
            realm = environment.config.realm
            verifier(verifier.verifier)
            validate { validateCredential(it) }
        }
    }

    routing {

        route("auth") {
            post("login") { user: UserAuth ->
                val response = authController.login(user)
                call.respond(response)
            }
            post("refresh") { body: TokenRequest ->
                val response = authController.refresh(body.token)
                call.respond(response)
            }
        }
    }
}

private fun validateCredential(credential: JWTCredential) =
    JWTPrincipal(credential.payload)

