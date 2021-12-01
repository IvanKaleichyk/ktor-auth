package com.kaleichyk.plugins

import com.kaleichyk.JwtVerifierService
import com.kaleichyk.controllers.AuthController
import com.kaleichyk.models.TokenRequest
import com.kaleichyk.models.UserAuth
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

fun Application.configureSecurity(controller: AuthController, verifier: JwtVerifierService, realm: String) {

    install(Authentication) {
        jwt {
            this.realm = realm
            verifier(verifier.verifier)
            validate { validateCredential(it) }
        }
    }

    routing {
        route("auth") {
            post("/login") { user: UserAuth ->
                val response = controller.login(user)
                call.respond(response)
            }
            post("/refresh") { body: TokenRequest ->
                val response = controller.refresh(body.token)
                call.respond(response)
            }
        }
    }
}

private fun validateCredential(credential: JWTCredential) =
    JWTPrincipal(credential.payload)

