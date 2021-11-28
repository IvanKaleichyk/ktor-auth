package com.kaleichyk.plugins

import com.kaleichyk.utils.extensions.algorithm
import com.kaleichyk.utils.extensions.issuer
import com.kaleichyk.utils.extensions.realm
import com.kaleichyk.utils.extensions.tokenValidTime
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

fun Application.configureSecurity() {

    routing {
        route("conf") {
            get {
                val conf = mapOf(
                    "issuer" to environment.config.issuer,
                    "realm" to environment.config.realm,
                    "tokenValidTime" to environment.config.tokenValidTime.toString(),
                    "algorithm" to environment.config.algorithm,
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
