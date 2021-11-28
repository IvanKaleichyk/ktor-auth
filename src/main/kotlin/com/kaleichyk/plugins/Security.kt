package com.kaleichyk.plugins

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

fun Application.configureSecurity() {

    routing {
        route("conf") {
            get {
                val conf = mapOf(
                    "secret" to environment.config.property("jwt.secret").getString(),
                    "issuer" to environment.config.property("jwt.issuer").getString(),
                )
                call.respond(conf.toString())
            }
        }
    }

//    authentication {
//        jwt {
//            val jwtAudience = environment.config.property("jwt.audience").getString()
//            realm = environment.config.property("jwt.realm").getString()
//            verifier(
//                JWT
//                    .require(Algorithm.HMAC256("secret"))
//                    .withAudience(jwtAudience)
//                    .withIssuer(environment.config.property("jwt.domain").getString())
//                    .build()
//            )
//            validate { credential ->
//                if (credential.payload.audience.contains(jwtAudience)) JWTPrincipal(credential.payload) else null
//            }
//        }
//    }

}
