package com.kaleichyk

import com.kaleichyk.controllers.AuthController
import com.kaleichyk.di.controllerModule
import com.kaleichyk.di.interactorModule
import com.kaleichyk.di.repositoryModule
import com.kaleichyk.di.serviceModule
import com.kaleichyk.di.utilsModule
import com.kaleichyk.plugins.configureSecurity
import com.kaleichyk.plugins.configureSerialization
import com.kaleichyk.plugins.configureUserRoute
import com.kaleichyk.utils.extensions.algorithm
import com.kaleichyk.utils.extensions.getExpressionTime
import com.kaleichyk.utils.extensions.issuer
import com.kaleichyk.utils.extensions.realm
import com.kaleichyk.utils.extensions.subject
import com.kaleichyk.utils.extensions.tokenValidTime
import com.kaleichyk.utils.validator.ValidateException
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.StatusPages
import io.ktor.http.HttpStatusCode
import io.ktor.request.httpMethod
import io.ktor.response.respond
import io.ktor.routing.routing
import io.ktor.server.netty.EngineMain
import javax.security.sasl.AuthenticationException
import org.koin.ktor.ext.Koin
import org.koin.ktor.ext.inject
import org.slf4j.event.Level

fun main(args: Array<String>) = EngineMain.main(args)

fun Application.module() {
    configureDI()
    configureStatusPage()
    configureLogger()
    configureSerialization()
    configureSecurity(inject<AuthController>().value, inject<JwtVerifierService>().value, realm)

    routing {
        configureUserRoute()
    }
}

fun Application.configureStatusPage() {
    install(StatusPages) {
        exception<AuthenticationException> {
            call.respond(HttpStatusCode.Unauthorized)
        }
        exception<ValidateException> {
            call.respond(HttpStatusCode.BadRequest, it.message)
        }
    }
}

fun Application.configureDI() {
    install(Koin) {
        modules(
            utilsModule,
            serviceModule(algorithm, issuer),
            repositoryModule(algorithm, subject, issuer, getExpressionTime(tokenValidTime)),
            interactorModule,
            controllerModule,
        )
    }
}

fun Application.configureLogger() {
    install(CallLogging) {
        level = Level.DEBUG
        format { call ->
            val status = call.response.status()
            val httpMethod = call.request.httpMethod.value
            val userAgent = call.request.headers["User-Agent"]
            "Status: $status, HTTP method: $httpMethod, User agent: $userAgent"
        }
    }
}
